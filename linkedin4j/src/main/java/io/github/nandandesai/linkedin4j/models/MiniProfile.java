package io.github.nandandesai.linkedin4j.models;

public class MiniProfile {
    private String publicIdentifier;
    private String name;
    private String occupation;
    private String tempProfilePicUrl;

    public MiniProfile(String publicIdentifier, String name, String occupation, String tempProfilePicUrl) {
        this.publicIdentifier = publicIdentifier;
        this.name = name;
        this.occupation = occupation;
        this.tempProfilePicUrl = tempProfilePicUrl;
    }

    public String getPublicIdentifier() {
        return publicIdentifier;
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getTempProfilePicUrl() {
        return tempProfilePicUrl;
    }
}
