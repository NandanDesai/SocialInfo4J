package io.github.nandandesai.facebookscraper4j.exceptions;

public class FacebookException extends Exception {
    private int statusCode=0;

    public FacebookException(String message, int statusCode){
        super(message);
        this.statusCode=statusCode;
    }

    public FacebookException(String message){
        super(message);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
