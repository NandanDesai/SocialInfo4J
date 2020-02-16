package io.github.nandandesai.instagramwrapper4j.models;

import java.util.Date;
import java.util.List;

public class Image {
    private String id;
    private String opUsername;
    private Integer noOfLikes;
    private Date timestamp;
    private Float latitude;
    private Float longitude;
    private Integer noOfComments;
    private List<ImageVersion> imageVersions;


    public static class ImageVersion{
        private Integer height;
        private Integer width;
        private String url;

        public ImageVersion(Integer height, Integer width, String url) {
            this.height = height;
            this.width = width;
            this.url = url;
        }

        public Integer getHeight() {
            return height;
        }

        public Integer getWidth() {
            return width;
        }

        public String getUrl() {
            return url;
        }
    }

    public Image(String id, String opUsername, Integer noOfLikes, Date timestamp, Float latitude, Float longitude, Integer noOfComments, List<ImageVersion> imageVersions) {
        this.id = id;
        this.opUsername = opUsername;
        this.noOfLikes = noOfLikes;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.noOfComments = noOfComments;
        this.imageVersions = imageVersions;
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

    public List<ImageVersion> getImageVersions() {
        return imageVersions;
    }
}
