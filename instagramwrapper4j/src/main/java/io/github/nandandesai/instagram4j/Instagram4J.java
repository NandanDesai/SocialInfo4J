/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.nandandesai.instagram4j;

import io.github.nandandesai.instagram4j.requests.InstagramLoginRequest;
import io.github.nandandesai.instagram4j.requests.InstagramRequest;
import io.github.nandandesai.instagram4j.requests.internal.*;
import io.github.nandandesai.instagram4j.requests.payload.InstagramLoginPayload;
import io.github.nandandesai.instagram4j.requests.payload.InstagramLoginResult;
import io.github.nandandesai.instagram4j.utils.InstagramGenericUtil;
import io.github.nandandesai.instagram4j.utils.InstagramHashUtil;
import okhttp3.*;

import java.io.IOException;
import java.io.Serializable;
import java.net.Proxy;
import java.util.*;


/**
 * 
 * Instagram4j API
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class Instagram4J implements Serializable {

    protected String deviceId;

    protected String uuid;

    protected String advertisingId;

    protected String username;

    protected String password;

    protected Proxy proxy;

    protected long userId;

    protected String rankToken;

    protected boolean isLoggedIn;

    //protected HttpResponse lastResponse;

    protected boolean debug;

    protected CookieJar cookieJar;


    //protected CloseableHttpClient client;
    protected OkHttpClient client;

//    protected String identifier;
//    protected String verificationCode;
//    protected String challengeUrl;

    /**
     * @param username Username
     * @param password Password
     */
    public Instagram4J(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
    
    /**
     * @param username Username
     * @param password Password
     * @param cookieJar Cookie Store
     * @param proxy proxy
     */
    public Instagram4J(String username, String password, CookieJar cookieJar, Proxy proxy) {
        super();
        this.username = username;
        this.password = password;
        this.cookieJar = cookieJar;
        this.proxy = proxy;
    }
    
    /**
     * Setup some variables
     */
    public void setup() {
        
        if (this.username==null) {
            throw new IllegalArgumentException("Username is mandatory.");
        }
        
        if (this.password==null) {
            throw new IllegalArgumentException("Password is mandatory.");
        }
        
        this.deviceId = InstagramHashUtil.generateDeviceId(this.username, this.password);
        
        if (this.uuid==null) {
            this.uuid = InstagramGenericUtil.generateUuid(true);
        }
        
        if (this.advertisingId==null) {
            this.advertisingId = InstagramGenericUtil.generateUuid(true);
        }
        
        if (this.cookieJar == null) {
            this.cookieJar = new InMemoryCookieJar();
        }

//        HttpClientBuilder builder = HttpClientBuilder.create();
//        if (proxy != null) {
//            builder.setProxy(proxy);
//        }
//
//        builder.setDefaultCookieStore(this.cookieJar);
//        this.client = builder.build();

        this.client=getOkHttpClient(proxy);
    }

    /**
     * @return
     * @throws IOException
     */
    public InstagramLoginResult login() throws IOException, IllegalAccessException, InstantiationException {

        this.sendRequest(new InstagramReadMsisdnHeaderRequest());
        this.sendRequest(new InstagramSyncFeaturesRequest(true));
        this.sendRequest(new InstagramZeroRatingTokenRequest());
        this.sendRequest(new InstagramLogAttributionRequest());

        InstagramLoginPayload loginRequest=new InstagramLoginPayload(username, InstagramGenericUtil.generateUuid(true)
        , getOrFetchCsrf(), uuid, deviceId, password, 0);
        InstagramLoginRequest req = new InstagramLoginRequest(loginRequest);
        InstagramLoginResult loginResult = this.sendRequest(req);
        emulateUserLoggedIn(loginResult);

//        if (loginResult.getTwo_factor_info() != null) {
//            identifier = loginResult.getTwo_factor_info().getTwo_factor_identifier();
//        } else if (loginResult.getChallenge() != null) {
//            // logic for challenge
//            log.info("Challenge required: " + loginResult.getChallenge());
//        }
        
        return loginResult;
    }



    /**
     * @return
     * @throws IOException
     */
    public String getOrFetchCsrf() throws IOException, IllegalAccessException, InstantiationException {
        Optional<Cookie> checkCookie = getCsrfCookie();
        if (!checkCookie.isPresent()) {
            sendRequest(new InstagramFetchHeadersRequest());
            checkCookie = getCsrfCookie();
        }
        String csrfToken = checkCookie.get().value();
        return csrfToken;
    }
    
    public Optional<Cookie> getCsrfCookie() {
        return cookieJar.loadForRequest(HttpUrl.parse(InstagramConstants.API_URL)).stream().filter(cookie->cookie.name().equalsIgnoreCase("csrftoken")).findFirst();
        //return cookieJar.getCookies().stream().filter(cookie -> cookie.getName().equalsIgnoreCase("csrftoken")).findFirst();
    }


    /**
     * Send request to endpoint
     * @param request Request object
     * @return success flag
     * @throws IOException
     */
    public <T> T sendRequest(InstagramRequest<T> request) throws IOException, InstantiationException, IllegalAccessException {


        if (!this.isLoggedIn
                && request.requiresLogin()) {
            throw new IllegalStateException("Need to login first!");
        }
        
        // wait to simulate real human interaction
        randomWait();
        
        request.setApi(this);
        T response = request.execute();

        
        return response;
    }

    private void emulateUserLoggedIn(InstagramLoginResult loginResult) throws IOException {
        if (loginResult.getStatus().equalsIgnoreCase("ok")) {
            this.userId = loginResult.getLogged_in_user().getPk();
            this.rankToken = this.userId + "_" + this.uuid;
            this.isLoggedIn = true;
//            this.sendRequest(new InstagramSyncFeaturesRequest(false));
//            this.sendRequest(new InstagramAutoCompleteUserListRequest());
//            this.sendRequest(new InstagramTimelineFeedRequest());
//            this.sendRequest(new InstagramGetInboxRequest());
//            this.sendRequest(new InstagramGetRecentActivityRequest());
        }
    }

    private void randomWait() {
        try {
            Thread.sleep(randomLongBetween(100, 250));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private long randomLongBetween(long min, long max) {
        Random rand = new Random();
        return min + (long) (rand.nextDouble() * (max - min));
    }

    private static Headers getAuthHeaders(){
        Headers headers= new Headers.Builder()
                    .add("Connection", "close")
                    .add("Accept", "*/*")
                    .add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .add("Cookie2", "$Version=1")
                    .add("Accept-Language", "en-US")
                    .add("User-Agent", InstagramConstants.USER_AGENT)
                    .build();
        return headers;
    }

    private OkHttpClient getOkHttpClient(Proxy proxy) {
        try {
            if(proxy!=null){
                return new OkHttpClient.Builder()
                        .proxy(proxy)
                        //.addInterceptor(new BasicAuthInterceptor(username, password))
                        .cookieJar(cookieJar)
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request newRequest = chain.request().newBuilder()
                                        .headers(getAuthHeaders())
                                        .build();
                                return chain.proceed(newRequest);
                            }
                        })
                        .build();
            }else {
                return new OkHttpClient.Builder()
                        .cookieJar(cookieJar)
                        //.addInterceptor(new BasicAuthInterceptor(username, password))
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request newRequest = chain.request().newBuilder()
                                        .headers(getAuthHeaders())
                                        .build();
                                return chain.proceed(newRequest);
                            }
                        })
                        .build();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private class InMemoryCookieJar implements CookieJar {

        private final Set<Cookie> cookieStore = new HashSet<>();

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            /**
             *Saves cookies from HTTP response
             * If the response includes a trailer this method is called second time
             */
            //Save cookies to the store
            cookieStore.addAll(cookies);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            /**
             * Load cookies from the jar for an HTTP request.
             * This method returns cookies that have not yet expired
             */
            List<Cookie> validCookies = new ArrayList<>();
            for (Cookie cookie : cookieStore) {
                if (cookie.expiresAt() < System.currentTimeMillis()) {
                    // invalid cookies
                } else {
                    validCookies.add(cookie);
                }
            }
            return validCookies;
        }
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getUuid() {
        return uuid;
    }

    public String getAdvertisingId() {
        return advertisingId;
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

    public long getUserId() {
        return userId;
    }

    public String getRankToken() {
        return rankToken;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public boolean isDebug() {
        return debug;
    }

    public CookieJar getCookieJar() {
        return cookieJar;
    }

    public OkHttpClient getClient() {
        return client;
    }
}