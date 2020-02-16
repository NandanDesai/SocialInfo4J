package io.github.nandandesai.instagramwrapper4j.models;

public class MiniProfile {
    private String username;
    private String profilePicUrl;
    private String name;
    private Boolean isVerified;
    private Boolean isPrivate;

    public MiniProfile(String username, String profilePicUrl, String name, Boolean isVerified, Boolean isPrivate) {
        this.username = username;
        this.profilePicUrl = profilePicUrl;
        this.name = name;
        this.isVerified = isVerified;
        this.isPrivate=isPrivate;
    }

    public String getUsername() {
        return username;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public String getName() {
        return name;
    }

    public Boolean isVerified() {
        return isVerified;
    }

    public Boolean isPrivate(){
        return isPrivate;
    }
}
