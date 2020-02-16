package io.github.nandandesai.linkedin4j;

import io.github.nandandesai.linkedin4j.exceptions.LinkedInException;
import io.github.nandandesai.linkedin4j.models.MiniProfile;
import io.github.nandandesai.linkedin4j.models.Profile;

import java.io.IOException;
import java.net.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkedIn {
    private String username;
    private String password;
    private Proxy proxy;

    private LinkedIn(String username, String password, Proxy proxy) throws IOException, LinkedInException {
        this.username=username;
        this.password=password;
        this.proxy=proxy;
        init();
    }

    private void init() throws LinkedInException, IOException {
        String result=ContentFetcher.init("https://www.linkedin.com/uas/authenticate",proxy);
        if(result==null){
            throw new LinkedInException("LinkedIn initiation failed");
        }
        result=ContentFetcher.login("https://www.linkedin.com/uas/authenticate",username ,password ,proxy);
        if(result==null){
            throw new LinkedInException("LinkedIn login failed");
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public Profile getProfile(String username) throws IOException {
        String url="https://www.linkedin.com/voyager/api/identity/profiles/"+username+"/profileView";
        String jsonResponse=ContentFetcher.apiCall(url, proxy);
        return Mapper.mapProfile(jsonResponse);
    }

    public List<MiniProfile> searchProfile(String query) throws IOException {
        Map<String, String> params=new HashMap<>();
        params.put("count", "20");
        params.put("filters","List(resultType->PEOPLE)");
        params.put("origin","GLOBAL_SEARCH_HEADER");
        params.put("q","all");
        params.put("start","0");
        params.put("queryContext","List(spellCorrectionEnabled->true,relatedSearchesEnabled->true,kcardTypes->PROFILE|COMPANY)");
        params.put("keywords",query);

        String url="https://www.linkedin.com/voyager/api/search/blended";
        String urlEncodedParams= URLEncoderUtility.mapToURL(url, params);
        System.out.println("urlEncodedParams: "+urlEncodedParams);
        String jsonResponse=ContentFetcher.apiCall(urlEncodedParams, proxy);
        return Mapper.mapMiniProfiles(jsonResponse);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String username=null;
        private String password=null;
        private Proxy proxy=null;

        private static LinkedIn linkedIn=null;

        public Builder username(String username){
            this.username=username;
            return this;
        }

        public Builder password(String password){
            this.password=password;
            return this;
        }

        public Builder proxy(Proxy proxy){
            this.proxy=proxy;
            return this;
        }

        public LinkedIn getInstance() throws LinkedInException, IOException {
            if(linkedIn==null) {
                if(username==null){
                    throw new IllegalStateException("username is not provided for the Builder");
                }
                if(password==null){
                    throw new IllegalStateException("password is not provided for the Builder");
                }
                linkedIn=new LinkedIn(username, password, proxy);
            }
            return linkedIn;
        }
    }
}
