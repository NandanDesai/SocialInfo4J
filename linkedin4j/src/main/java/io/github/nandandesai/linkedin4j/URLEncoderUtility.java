package io.github.nandandesai.linkedin4j;


import java.util.Map;

public class URLEncoderUtility {

    static String mapToURL(String url, Map<String, String> parameters) {
        if (parameters == null) {
            return url;
        }
        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            String key = parameter.getKey();
            String value = parameter.getValue();

            String encodedKey = urlEncode(key);
            String encodedValue = urlEncode(value);
            if (!url.contains("?")) {
                url += "?" + encodedKey + "=" + encodedValue;
            } else {
                url += "&" + encodedKey + "=" + encodedValue;
            }
        }
        return url;
    }

    //taken from https://github.com/pv342/java_url_encoder/blob/master/MyUrl.java
    private static String urlEncode(String text){   //encodes name or value and returns result
        String nonEncodeText = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789(),";
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
