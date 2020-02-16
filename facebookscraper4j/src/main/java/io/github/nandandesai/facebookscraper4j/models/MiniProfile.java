package io.github.nandandesai.facebookscraper4j.models;

public class MiniProfile {
    private String username; //might be a long numerical ID or a username kind of a thing
    private String name;
    private String profilePicUrl;
    private String description;

    public MiniProfile(String username, String name, String profilePicUrl, String description) {
        this.username = username;
        this.name = name;
        this.profilePicUrl = profilePicUrl;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public String getDescription() {
        return description;
    }
}
