package io.github.nandandesai.instagramwrapper4j.models;

import java.util.Date;
import java.util.List;

public class Video {
    private String id;
    private String opUsername;
    private Integer noOfLikes;
    private Date timestamp;
    private Float latitude;
    private Float longitude;
    private Integer noOfComments;


    private Double videoDuration;
    private Integer noOfViews;
    private List<VideoVersion> videoVersions;

    public static class VideoVersion{
        private String url;

        public VideoVersion(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }

    public Video(String id, String opUsername, Integer noOfLikes, Date timestamp, Float latitude, Float longitude, Integer noOfComments, Double videoDuration, Integer noOfViews, List<VideoVersion> videoVersions) {
        this.id = id;
        this.opUsername = opUsername;
        this.noOfLikes = noOfLikes;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.noOfComments = noOfComments;
        this.videoDuration = videoDuration;
        this.noOfViews = noOfViews;
        this.videoVersions = videoVersions;
    }

    public String getId() {
        return id;
    }

    public String getOpUsername() {
        return opUsername;
    }

    public Integer getNoOfLikes() {
        return noOfLikes;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public Integer getNoOfComments() {
        return noOfComments;
    }

    public Double getVideoDuration() {
        return videoDuration;
    }

    public Integer getNoOfViews() {
        return noOfViews;
    }

    public List<VideoVersion> getVideoVersions() {
        return videoVersions;
    }
}
