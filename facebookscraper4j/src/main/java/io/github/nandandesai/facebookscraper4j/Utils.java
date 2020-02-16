package io.github.nandandesai.facebookscraper4j;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;

class Utils {
    private static Map<String, String> getHttpHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:58.0) Gecko/20100101 Firefox/58.0");
        headers.put("Accept-Language", "en-US,en;q=0.5");
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        headers.put("Accept-Charset", "utf-8");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Connection", "keep-alive");
        headers.put("Upgrade-Insecure-Requests", "1");
        return headers;
    }

    static Connection getJsoupConnection(String url, Proxy proxy){
        if(proxy!=null){
            return Jsoup.connect(url).headers(getHttpHeaders()).ignoreHttpErrors(true).followRedirects(true).proxy(proxy);
        }else{
            return Jsoup.connect(url).headers(getHttpHeaders()).ignoreHttpErrors(true).followRedirects(true);
        }
    }

    static String getProperUsername(String userProfileLink) {
        String username = userProfileLink.replace("/", "").replaceAll("\\?.*", "");
        if (username.equalsIgnoreCase("profile.php")) {
            String[] urlParts = userProfileLink.split("[\\?&]");
            for (String urlPart : urlParts) {
                String[] keyValues = urlPart.split("=");
                if (keyValues[0].equalsIgnoreCase("id")) {
                    username = keyValues[1];
                }
            }
        }
        return username;
    }

    //taken from https://github.com/pv342/java_url_encoder/blob/master/MyUrl.java
    static String urlEncode(String text){
        String nonEncodeText = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String hex = "";
        int count = 0;
        String encodeText = "";
        //loop to analyze and or encode the entire string
        for (count = 0; count < text.length(); count++) {
            hex = "";
            char test = text.charAt(count);
            if (test == ' ')
                encodeText = encodeText + "+";
            else if ((nonEncodeText.indexOf(test)) >= 0)
                encodeText = encodeText + test;
            else {
                hex = Integer.toHexString(test);
                encodeText = encodeText + '%' + hex;
            }
        }
        return encodeText;
    }
}
