package io.github.nandandesai.facebookscraper4j;

import io.github.nandandesai.facebookscraper4j.exceptions.FacebookException;
import io.github.nandandesai.facebookscraper4j.models.MiniProfile;
import io.github.nandandesai.facebookscraper4j.models.Photo;
import io.github.nandandesai.facebookscraper4j.models.Profile;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.Proxy;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class FacebookScraper {

    private Map<String, String> cookies;
    private Proxy proxy;

    private FacebookScraper(String username, String password, Proxy proxy) throws IOException, FacebookException {
        this.proxy=proxy;
        login(username, password);
    }

    private void login(String email, String password) throws FacebookException, IOException {
        Connection.Response response = Utils.getJsoupConnection("https://www.facebook.com/?_fb_noscript=1", proxy)
                .method(Connection.Method.GET)
                .execute();

        cookies=response.cookies();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Document homepage=response.parse();
        //System.out.println(homepage);
        Element loginForm=homepage.getElementById("login_form");
        if(loginForm==null){
            throw new FacebookException("Login form doesn't exists in the HTML retrieved. Something went wrong. Try again!");
        }
        String loginUrl=loginForm.attr("action");
        //System.out.println("Login URL: "+loginUrl);

        String jazoestValue=loginForm.getElementsByAttributeValue("name","jazoest").first().attr("value");
        //System.out.println("Jazoest value: "+jazoestValue);

        String lsdValue=loginForm.getElementsByAttributeValue("name","lsd").first().attr("value");
        //System.out.println("Lsd value: "+lsdValue);

        String lgnrndValue=loginForm.getElementsByAttributeValue("name","lgnrnd").first().attr("value");
        //System.out.println("lgnrnd value: "+lgnrndValue);

        Connection.Response loginResponse = Utils.getJsoupConnection(loginUrl, proxy)
                .data("email", email)
                .data("pass", password)
                .data("jazoest",jazoestValue)
                .data("lsd",lsdValue)
                .data("timezone","")
                .data("lgndim","")
                .data("lgnrnd",lgnrndValue)
                .data("lgnjs","n")
                .data("ab_test_data","")
                .data("locale","en_US")
                .data("next","https://www.facebook.com/")
                .data("login_source","login_bluebar")
                .data("guid","")
                .data("prefill_contact_point","")
                .data("prefill_source","")
                .data("prefill_type","")
                .cookies(cookies)
                .method(Connection.Method.POST)
                .execute();

        cookies=loginResponse.cookies();
        //https://m.facebook.com/search/top/?q=mark+zuckerberg&refid=8&_rdr
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Connection.Response loggedInResponse = Utils.getJsoupConnection("https://m.facebook.com/search/top/?q=OSINT+community&refid=46&_rdr", proxy)
                .header("Referer","https://www.facebook.com/?_fb_noscript=1")
                .method(Connection.Method.GET)
                .cookies(cookies)
                .execute();

        Document doc=loggedInResponse.parse();

        //if any errors like invalid username/password or temporary account lock, then the title will be "Page Not Found"
        //if success, then the title will be "Results for "osint community""
        if(!doc.title().equals("Results for \"osint community\"")) {
            throw new FacebookException("Login Failed. Maybe due to incorrect username/password or your account might have been temporarily locked.");
        }
    }

    public Profile getProfile(String username) throws IOException, FacebookException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ProfileScraper profileScraper=new ProfileScraper();
        return profileScraper.getProfile(username, cookies, proxy);
    }

    public Iterator<List<String>> getFriends(String username){
        return new FriendsIterator(username, cookies, proxy);
    }

    public List<MiniProfile> searchUsers(String query) throws IOException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SearchScraper searchScraper=new SearchScraper();
        return searchScraper.searchUsers(query, cookies, proxy);
    }

    public List<Photo> getPhotos(String username) throws IOException, FacebookException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PhotoScraper photoScraper=new PhotoScraper(cookies, proxy);
        return photoScraper.getAllPhotos(username);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String username=null;
        private String password=null;
        private Proxy proxy=null;

        private static FacebookScraper facebookScraper=null;

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

        public FacebookScraper getInstance() throws FacebookException, IOException {
            if(facebookScraper==null) {
                if(username==null){
                    throw new IllegalStateException("username is not provided for the Builder");
                }
                if(password==null){
                    throw new IllegalStateException("password is not provided for the Builder");
                }
                facebookScraper=new FacebookScraper(username, password, proxy);
            }
            return facebookScraper;
        }
    }
}
