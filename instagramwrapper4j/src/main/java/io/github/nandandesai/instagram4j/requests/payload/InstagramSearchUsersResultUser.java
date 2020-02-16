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

import java.util.Map;

public class InstagramSearchUsersResultUser {

    public int unseen_count;
    public String username;
    public String byline;
    public String profile_pic_url;
    public boolean has_anonymous_profile_picture;
    public Map<String, Object> friendship_status;
    public boolean is_private;
    public boolean is_verified;
    public int mutual_followers_count;
    public String full_name;
    public long pk;
    public int follower_count;
    public String profile_pic_id;
    public String social_context;
    public String search_social_context;

    public int getUnseen_count() {
        return unseen_count;
    }

    public void setUnseen_count(int unseen_count) {
        this.unseen_count = unseen_count;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getProfile_pic_url() {
        return profile_pic_url;
    }

    public void setProfile_pic_url(String profile_pic_url) {
        this.profile_pic_url = profile_pic_url;
    }

    public boolean isHas_anonymous_profile_picture() {
        return has_anonymous_profile_picture;
    }

    public void setHas_anonymous_profile_picture(boolean has_anonymous_profile_picture) {
        this.has_anonymous_profile_picture = has_anonymous_profile_picture;
    }

    public Map<String, Object> getFriendship_status() {
        return friendship_status;
    }

    public void setFriendship_status(Map<String, Object> friendship_status) {
        this.friendship_status = friendship_status;
    }

    public boolean isIs_private() {
        return is_private;
    }

    public void setIs_private(boolean is_private) {
        this.is_private = is_private;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public int getMutual_followers_count() {
        return mutual_followers_count;
    }

    public void setMutual_followers_count(int mutual_followers_count) {
        this.mutual_followers_count = mutual_followers_count;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    public int getFollower_count() {
        return follower_count;
    }

    public void setFollower_count(int follower_count) {
        this.follower_count = follower_count;
    }

    public String getProfile_pic_id() {
        return profile_pic_id;
    }

    public void setProfile_pic_id(String profile_pic_id) {
        this.profile_pic_id = profile_pic_id;
    }

    public String getSocial_context() {
        return social_context;
    }

    public void setSocial_context(String social_context) {
        this.social_context = social_context;
    }

    public String getSearch_social_context() {
        return search_social_context;
    }

    public void setSearch_social_context(String search_social_context) {
        this.search_social_context = search_social_context;
    }
}