package io.github.nandandesai.instagramwrapper4j;

import io.github.nandandesai.instagram4j.Instagram4J;
import io.github.nandandesai.instagram4j.requests.InstagramSearchUsernameRequest;
import io.github.nandandesai.instagram4j.requests.InstagramSearchUsersRequest;
import io.github.nandandesai.instagram4j.requests.InstagramUserFeedRequest;
import io.github.nandandesai.instagram4j.requests.payload.*;
import io.github.nandandesai.instagramwrapper4j.models.*;
import okhttp3.CookieJar;

import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Instagram {
    private Instagram4J instagram4j;

    private Instagram(Instagram4J instagram4j) {
        this.instagram4j = instagram4j;
    }

    public Profile getProfile(String queryUsername) throws IOException, IllegalAccessException, InstantiationException {
        InstagramSearchUsernameResult userResult = instagram4j.sendRequest(new InstagramSearchUsernameRequest(queryUsername));
        InstagramUser user = userResult.getUser();

        Long id=user.getPk();
        String username=user.getUsername();
        String name=user.getFull_name();
        String bio=user.getBiography();
        Integer mediaCount=user.getMedia_count();
        Integer noOfFollowers=user.getFollower_count();
        Integer noOfFollowing=user.getFollowing_count();
        String profilePicUrl=user.getHd_profile_pic_url_info().getUrl();
        Float latitude=user.getLatitude();
        Float longitude=user.getLongitude();
        String cityName=user.getCity_name();
        String streetAddress=user.getAddress_street();
        String zip=user.getZip();
        Boolean isVerified=user.isIs_verified();
        Boolean isPrivate=user.isIs_private();

        return new Profile(id, username, name, bio, mediaCount, noOfFollowers, noOfFollowing, profilePicUrl,latitude,longitude,cityName,streetAddress,zip,isVerified,isPrivate);

    }

    public UserTimeline getUserTimeline(String queryUsername) throws IOException, IllegalAccessException, InstantiationException {
        InstagramSearchUsernameResult userResult = instagram4j.sendRequest(new InstagramSearchUsernameRequest(queryUsername));
        InstagramUser user = userResult.getUser();
        InstagramFeedResult timelineFeed = instagram4j.sendRequest(new InstagramUserFeedRequest(user.getPk()));

        List<Image> images=new ArrayList<>();
        List<Video> videos=new ArrayList<>();

        while (timelineFeed.isMore_available()) {
            List<InstagramFeedItem> feedItems = timelineFeed.getItems();
            for (InstagramFeedItem item : feedItems) {
                String id=item.getId();
                String opUsername=item.getUser().getUsername();
                Integer noOfLikes=item.getLike_count();
                Date timestamp=new Date(item.getTaken_at());
                Float latitude=item.getLat();
                Float longitude=item.getLng();
                Integer noOfComments=item.getComment_count();
                if (item.getVideo_duration() == 0) {
                    //this item is an image
                    List<Image.ImageVersion> imageVers=new ArrayList<>();
                    ImageVersions imageVersions = item.getImage_versions2();
                    if (imageVersions != null) {
                        List<ImageMeta> imageMetaList = imageVersions.getCandidates();
                        for (ImageMeta meta : imageMetaList) {
                            Image.ImageVersion imageVersion=new Image.ImageVersion(meta.getHeight(), meta.getWidth(), meta.getUrl());
                            imageVers.add(imageVersion);
                        }
                        images.add(new Image(id, opUsername, noOfLikes, timestamp, latitude, longitude, noOfComments, imageVers));
                    }
                } else {
                    //this item is a video
                    List<Video.VideoVersion> videoVersions=new ArrayList<>();
                    Double videoDuration=item.getVideo_duration();
                    Integer noOfViews=item.getView_count();
                    List<ImageMeta> imageMetaList = item.getVideo_versions();
                    for (ImageMeta meta : imageMetaList) {
                        videoVersions.add(new Video.VideoVersion(meta.getUrl()));
                    }
                    videos.add(new Video(id, opUsername, noOfLikes, timestamp, latitude, longitude, noOfComments, videoDuration, noOfViews, videoVersions));
                }
            }
            timelineFeed = instagram4j.sendRequest(new InstagramUserFeedRequest(user.getPk(), timelineFeed.getNext_max_id(), 0, 0));
        }
        return new UserTimeline(queryUsername, images, videos);
    }

    public List<MiniProfile> searchUsers(String query) throws IOException, IllegalAccessException, InstantiationException {
        List<MiniProfile> searchResults=new ArrayList<>();

        InstagramSearchUsersResult searchUsersResults=instagram4j.sendRequest(new InstagramSearchUsersRequest(query));
        List<InstagramSearchUsersResultUser> results=searchUsersResults.getUsers();
        for(InstagramSearchUsersResultUser resultUser:results){
            String username=resultUser.getUsername();
            String profilePicUrl=resultUser.getProfile_pic_url();
            String name=resultUser.getFull_name();
            Boolean isVerified=resultUser.isIs_verified();
            Boolean isPrivate=resultUser.isIs_private();
            searchResults.add(new MiniProfile(username, profilePicUrl, name, isVerified, isPrivate));
        }

        return searchResults;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username = null;
        private String password = null;
        private CookieJar cookieJar=null;
        private Proxy proxy=null;

        private static Instagram instagram = null;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder cookieJar(CookieJar cookieJar){
            this.cookieJar=cookieJar;
            return this;
        }

        public Builder proxy(Proxy proxy){
            this.proxy=proxy;
            return this;
        }


        public Instagram getInstance() throws IOException, InstantiationException, IllegalAccessException {
            if (username == null) {
                throw new IllegalStateException("username is not provided for the Builder");
            }
            if (password == null) {
                throw new IllegalStateException("password is not provided for the Builder");
            }
            if (instagram == null) {
                Instagram4J instagram4j = new Instagram4J(username,password,cookieJar,proxy);
                instagram4j.setup();
                instagram4j.login();
                instagram = new Instagram(instagram4j);
            }
            return instagram;
        }
    }
}
