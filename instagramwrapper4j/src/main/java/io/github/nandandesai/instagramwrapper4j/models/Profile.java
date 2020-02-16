package io.github.nandandesai.instagramwrapper4j.models;

public class Profile {
    private Long id;
    private String username;
    private String name;
    private String bio;
    private Integer mediaCount;
    private Integer noOfFollowers;
    private Integer noOfFollowing;
    private String profilePicUrl;
    private Float latitude;
    private Float longitude;
    private String cityName;
    private String streetAddress;
    private String zip;
    private Boolean isVerified;
    private Boolean isPrivate;

    public Profile(Long id, String username, String name, String bio, Integer mediaCount, Integer noOfFollowers, Integer noOfFollowing, String profilePicUrl, Float latitude, Float longitude, String cityName, String streetAddress, String zip, Boolean isVerified, Boolean isPrivate) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.bio = bio;
        this.mediaCount = mediaCount;
        this.noOfFollowers = noOfFollowers;
        this.noOfFollowing = noOfFollowing;
        this.profilePicUrl = profilePicUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cityName = cityName;
        this.streetAddress = streetAddress;
        this.zip = zip;
        this.isVerified = isVerified;
        this.isPrivate=isPrivate;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public Integer getMediaCount() {
        return mediaCount;
    }

    public Integer getNoOfFollowers() {
        return noOfFollowers;
    }

    public Integer getNoOfFollowing() {
        return noOfFollowing;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public String getCityName() {
        return cityName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getZip() {
        return zip;
    }

    public Boolean isVerified() {
        return isVerified;
    }

    public Boolean isPrivate() {
        return isPrivate;
    }
}
