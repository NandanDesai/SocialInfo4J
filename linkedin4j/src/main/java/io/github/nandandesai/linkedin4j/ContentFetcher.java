package io.github.nandandesai.linkedin4j;

import okhttp3.*;
import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class ContentFetcher {
    private static String jsession="";
    static String login(String url, String username, String password, Proxy proxy) throws IOException {
        RequestBody requestBody = new FormBody.Builder()
                .add("session_key", username)
                .add("session_password", password)
                .add("JSESSIONID",jsession)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        OkHttpClient client = getOkHttpClient(proxy);
        Call call=client.newCall(request);
        Response response = call.execute();
        String res=null;
        if(response.code()==200) {
            res=response.body().string();
        }else{
            System.out.println(response);
        }
        response.close();
        call.cancel();
        client.connectionPool().evictAll();
        return res;
    }

    static String init(String url, Proxy proxy) throws IOException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        OkHttpClient client = getOkHttpClient(proxy);
        Request request = new Request.Builder().url(url).build();
        Call call=client.newCall(request);
        Response response = call.execute();
        String res=null;
        if(response.code()==200) {
            res=response.body().string();
        }else{
            System.out.println(response);
        }
        response.close();
        call.cancel();
        client.connectionPool().evictAll();
        return res;
    }

    static String apiCall(String url, Proxy proxy) throws IOException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        OkHttpClient client = getOkHttpClient(proxy);
        Request request = new Request.Builder().url(url).build();
        Call call=client.newCall(request);
        Response response = call.execute();
        String res=null;
        if(response.code()==200) {
            res=response.body().string();
        }else{
            System.out.println(response);
        }
        response.close();
        call.cancel();
        client.connectionPool().evictAll();

        return res;
    }

    private static Headers getAuthHeaders(){
        Headers headers=null;
        if(jsession!=null){
            String csrfToken=jsession.replace("\"","");
            headers= new Headers.Builder()
                    .add("X-Li-User-Agent","LIAuthLibrary:3.2.4 com.linkedin.LinkedIn:8.8.1 iPhone:8.3")
                    .add("User-Agent","LinkedIn/8.8.1 CFNetwork/711.3.18 Darwin/14.0.0")
                    .add("X-User-Language","en")
                    .add("X-User-Locale","en_US")
                    .add("Accept-Language","en-us")
                    .add("csrf-token",csrfToken)
                    .add("x-restli-protocol-version", "2.0.0")
                    //.add("accept", "application/vnd.linkedin.normalized+json+2.1")
                    .build();
        }else{
            headers= new Headers.Builder()
                    .add("X-Li-User-Agent","LIAuthLibrary:3.2.4 com.linkedin.LinkedIn:8.8.1 iPhone:8.3")
                    .add("User-Agent","LinkedIn/8.8.1 CFNetwork/711.3.18 Darwin/14.0.0")
                    .add("X-User-Language","en")
                    .add("X-User-Locale","en_US")
                    .add("Accept-Language","en-us")
                    .add("x-restli-protocol-version", "2.0.0")
                    //.add("accept", "application/vnd.linkedin.normalized+json+2.1")
                    .build();
        }
        return headers;
    }

    private static OkHttpClient getOkHttpClient(Proxy proxy) {
        try {
            if(proxy!=null){
                return new OkHttpClient.Builder()
                        .proxy(proxy)
                        .cookieJar(new MyCookieJar())
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
                        .cookieJar(new MyCookieJar())
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

    public static class MyCookieJar implements CookieJar {
        private static final String LINKEDIN_AUTH_COOKIE = "linkedin_auth_cookie";
        private static HashMap<String, List<Cookie>> cookieStore=new HashMap<>();
        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            if(url.encodedPath().contains("/voyager/api")){
                return;
            }
            for(Cookie cookie:cookies){
                if(cookie.name().equals("JSESSIONID")){
                    jsession=cookie.value();
                }
            }
            cookieStore.put(LINKEDIN_AUTH_COOKIE,cookies);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies=cookieStore.get(LINKEDIN_AUTH_COOKIE);
            if(cookies==null){
                return new ArrayList<Cookie>();
            }
            return cookies;
        }
    }

}
