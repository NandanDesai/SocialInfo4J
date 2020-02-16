package io.github.nandandesai.instagramwrapper4j.models;

import java.util.List;

public class UserTimeline {
    private String username;
    private List<Image> images;
    private List<Video> videos;

    public UserTimeline(String username, List<Image> images, List<Video> videos) {
        this.username = username;
        this.images = images;
        this.videos = videos;
    }

    public String getUsername() {
        return username;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<Video> getVideos() {
        return videos;
    }
}
