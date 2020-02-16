/**
 * Copyright (C) 2016 Bruno Candido Volpato da Cunha (brunocvcunha@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.nandandesai.instagram4j.requests.payload;


import java.util.List;
import java.util.Map;

/**
 * Tag Feed Results
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class InstagramFeedItem {

    public long taken_at;
    public long pk;
    public String id;
    public long device_timestamp;
    public int media_type;
    public String code;
    public String client_cache_key;
    public int filter_type;
    public boolean has_audio;
    public double video_duration;
    public Map<String, Object> attribution;
    public List<ImageMeta> video_versions;
    public ImageVersions image_versions2;
    public InstagramUserTagsContainer usertags;
    public FeedItemLocation location;
    public float lng;
    public float lat;
    public int original_width;
    public int original_height;
    public int view_count;
    public InstagramUser user;

    public List<InstagramCarouselMediaItem> carousel_media;
    
    public String organic_tracking_token;
    public int like_count;
    public List<String> top_likers;
    public List<InstagramUserSummary> likers;
    public boolean has_liked;
    public boolean comment_likes_enabled;
    public boolean has_more_comments;
    public long next_max_id;
    public int max_num_visible_preview_comments;
    public List<InstagramComment> preview_comments;
    public List<Object> comments;
    public int comment_count;
    public InstagramComment caption;

    public boolean can_viewer_reshare;
    public boolean caption_is_edited;
    public boolean photo_of_you;
    public boolean comments_disabled;
    public boolean can_viewer_save;
    public boolean has_viewer_saved;

    public long getTaken_at() {
        return taken_at;
    }

    public void setTaken_at(long taken_at) {
        this.taken_at = taken_at;
    }

    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDevice_timestamp() {
        return device_timestamp;
    }

    public void setDevice_timestamp(long device_timestamp) {
        this.device_timestamp = device_timestamp;
    }

    public int getMedia_type() {
        return media_type;
    }

    public void setMedia_type(int media_type) {
        this.media_type = media_type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClient_cache_key() {
        return client_cache_key;
    }

    public void setClient_cache_key(String client_cache_key) {
        this.client_cache_key = client_cache_key;
    }

    public int getFilter_type() {
        return filter_type;
    }

    public void setFilter_type(int filter_type) {
        this.filter_type = filter_type;
    }

    public boolean isHas_audio() {
        return has_audio;
    }

    public void setHas_audio(boolean has_audio) {
        this.has_audio = has_audio;
    }

    public double getVideo_duration() {
        return video_duration;
    }

    public void setVideo_duration(double video_duration) {
        this.video_duration = video_duration;
    }

    public Map<String, Object> getAttribution() {
        return attribution;
    }

    public void setAttribution(Map<String, Object> attribution) {
        this.attribution = attribution;
    }

    public List<ImageMeta> getVideo_versions() {
        return video_versions;
    }

    public void setVideo_versions(List<ImageMeta> video_versions) {
        this.video_versions = video_versions;
    }

    public ImageVersions getImage_versions2() {
        return image_versions2;
    }

    public void setImage_versions2(ImageVersions image_versions2) {
        this.image_versions2 = image_versions2;
    }

    public InstagramUserTagsContainer getUsertags() {
        return usertags;
    }

    public void setUsertags(InstagramUserTagsContainer usertags) {
        this.usertags = usertags;
    }

    public FeedItemLocation getLocation() {
        return location;
    }

    public void setLocation(FeedItemLocation location) {
        this.location = location;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public int getOriginal_width() {
        return original_width;
    }

    public void setOriginal_width(int original_width) {
        this.original_width = original_width;
    }

    public int getOriginal_height() {
        return original_height;
    }

    public void setOriginal_height(int original_height) {
        this.original_height = original_height;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public InstagramUser getUser() {
        return user;
    }

    public void setUser(InstagramUser user) {
        this.user = user;
    }

    public List<InstagramCarouselMediaItem> getCarousel_media() {
        return carousel_media;
    }

    public void setCarousel_media(List<InstagramCarouselMediaItem> carousel_media) {
        this.carousel_media = carousel_media;
    }

    public String getOrganic_tracking_token() {
        return organic_tracking_token;
    }

    public void setOrganic_tracking_token(String organic_tracking_token) {
        this.organic_tracking_token = organic_tracking_token;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public List<String> getTop_likers() {
        return top_likers;
    }

    public void setTop_likers(List<String> top_likers) {
        this.top_likers = top_likers;
    }

    public List<InstagramUserSummary> getLikers() {
        return likers;
    }

    public void setLikers(List<InstagramUserSummary> likers) {
        this.likers = likers;
    }

    public boolean isHas_liked() {
        return has_liked;
    }

    public void setHas_liked(boolean has_liked) {
        this.has_liked = has_liked;
    }

    public boolean isComment_likes_enabled() {
        return comment_likes_enabled;
    }

    public void setComment_likes_enabled(boolean comment_likes_enabled) {
        this.comment_likes_enabled = comment_likes_enabled;
    }

    public boolean isHas_more_comments() {
        return has_more_comments;
    }

    public void setHas_more_comments(boolean has_more_comments) {
        this.has_more_comments = has_more_comments;
    }

    public long getNext_max_id() {
        return next_max_id;
    }

    public void setNext_max_id(long next_max_id) {
        this.next_max_id = next_max_id;
    }

    public int getMax_num_visible_preview_comments() {
        return max_num_visible_preview_comments;
    }

    public void setMax_num_visible_preview_comments(int max_num_visible_preview_comments) {
        this.max_num_visible_preview_comments = max_num_visible_preview_comments;
    }

    public List<InstagramComment> getPreview_comments() {
        return preview_comments;
    }

    public void setPreview_comments(List<InstagramComment> preview_comments) {
        this.preview_comments = preview_comments;
    }

    public List<Object> getComments() {
        return comments;
    }

    public void setComments(List<Object> comments) {
        this.comments = comments;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public InstagramComment getCaption() {
        return caption;
    }

    public void setCaption(InstagramComment caption) {
        this.caption = caption;
    }

    public boolean isCan_viewer_reshare() {
        return can_viewer_reshare;
    }

    public void setCan_viewer_reshare(boolean can_viewer_reshare) {
        this.can_viewer_reshare = can_viewer_reshare;
    }

    public boolean isCaption_is_edited() {
        return caption_is_edited;
    }

    public void setCaption_is_edited(boolean caption_is_edited) {
        this.caption_is_edited = caption_is_edited;
    }

    public boolean isPhoto_of_you() {
        return photo_of_you;
    }

    public void setPhoto_of_you(boolean photo_of_you) {
        this.photo_of_you = photo_of_you;
    }

    public boolean isComments_disabled() {
        return comments_disabled;
    }

    public void setComments_disabled(boolean comments_disabled) {
        this.comments_disabled = comments_disabled;
    }

    public boolean isCan_viewer_save() {
        return can_viewer_save;
    }

    public void setCan_viewer_save(boolean can_viewer_save) {
        this.can_viewer_save = can_viewer_save;
    }

    public boolean isHas_viewer_saved() {
        return has_viewer_saved;
    }

    public void setHas_viewer_saved(boolean has_viewer_saved) {
        this.has_viewer_saved = has_viewer_saved;
    }
}
