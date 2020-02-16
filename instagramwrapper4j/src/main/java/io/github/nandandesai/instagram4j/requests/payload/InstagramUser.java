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

/**
 * User VO
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class InstagramUser {
    public boolean is_private;
    public boolean is_verified;
    public String username;
    public boolean has_chaining;
    public boolean is_business;
    public int media_count;
    public String profile_pic_id;
    public String external_url;
    public String full_name;
    public boolean has_biography_translation;
    public boolean has_anonymous_profile_picture;
    public boolean is_favorite;
    public String public_phone_country_code;
    public String public_phone_number;
    public String public_email;
    public long pk;
    public int geo_media_count;
    public int usertags_count;
    public String profile_pic_url;
    public String address_street;
    public String city_name;
    public String zip;
    public String direct_messaging;
    public String business_contact_method;
    public String biography;
    public int follower_count;
    public List<InstagramProfilePic> hd_profile_pic_versions;
    public InstagramProfilePic hd_profile_pic_url_info;
    public String external_lynx_url;
    public int following_count;
    public float latitude;
    public float longitude;
    public String category;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isHas_chaining() {
        return has_chaining;
    }

    public void setHas_chaining(boolean has_chaining) {
        this.has_chaining = has_chaining;
    }

    public boolean isIs_business() {
        return is_business;
    }

    public void setIs_business(boolean is_business) {
        this.is_business = is_business;
    }

    public int getMedia_count() {
        return media_count;
    }

    public void setMedia_count(int media_count) {
        this.media_count = media_count;
    }

    public String getProfile_pic_id() {
        return profile_pic_id;
    }

    public void setProfile_pic_id(String profile_pic_id) {
        this.profile_pic_id = profile_pic_id;
    }

    public String getExternal_url() {
        return external_url;
    }

    public void setExternal_url(String external_url) {
        this.external_url = external_url;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public boolean isHas_biography_translation() {
        return has_biography_translation;
    }

    public void setHas_biography_translation(boolean has_biography_translation) {
        this.has_biography_translation = has_biography_translation;
    }

    public boolean isHas_anonymous_profile_picture() {
        return has_anonymous_profile_picture;
    }

    public void setHas_anonymous_profile_picture(boolean has_anonymous_profile_picture) {
        this.has_anonymous_profile_picture = has_anonymous_profile_picture;
    }

    public boolean isIs_favorite() {
        return is_favorite;
    }

    public void setIs_favorite(boolean is_favorite) {
        this.is_favorite = is_favorite;
    }

    public String getPublic_phone_country_code() {
        return public_phone_country_code;
    }

    public void setPublic_phone_country_code(String public_phone_country_code) {
        this.public_phone_country_code = public_phone_country_code;
    }

    public String getPublic_phone_number() {
        return public_phone_number;
    }

    public void setPublic_phone_number(String public_phone_number) {
        this.public_phone_number = public_phone_number;
    }

    public String getPublic_email() {
        return public_email;
    }

    public void setPublic_email(String public_email) {
        this.public_email = public_email;
    }

    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    public int getGeo_media_count() {
        return geo_media_count;
    }

    public void setGeo_media_count(int geo_media_count) {
        this.geo_media_count = geo_media_count;
    }

    public int getUsertags_count() {
        return usertags_count;
    }

    public void setUsertags_count(int usertags_count) {
        this.usertags_count = usertags_count;
    }

    public String getProfile_pic_url() {
        return profile_pic_url;
    }

    public void setProfile_pic_url(String profile_pic_url) {
        this.profile_pic_url = profile_pic_url;
    }

    public String getAddress_street() {
        return address_street;
    }

    public void setAddress_street(String address_street) {
        this.address_street = address_street;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getDirect_messaging() {
        return direct_messaging;
    }

    public void setDirect_messaging(String direct_messaging) {
        this.direct_messaging = direct_messaging;
    }

    public String getBusiness_contact_method() {
        return business_contact_method;
    }

    public void setBusiness_contact_method(String business_contact_method) {
        this.business_contact_method = business_contact_method;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public int getFollower_count() {
        return follower_count;
    }

    public void setFollower_count(int follower_count) {
        this.follower_count = follower_count;
    }

    public List<InstagramProfilePic> getHd_profile_pic_versions() {
        return hd_profile_pic_versions;
    }

    public void setHd_profile_pic_versions(List<InstagramProfilePic> hd_profile_pic_versions) {
        this.hd_profile_pic_versions = hd_profile_pic_versions;
    }

    public InstagramProfilePic getHd_profile_pic_url_info() {
        return hd_profile_pic_url_info;
    }

    public void setHd_profile_pic_url_info(InstagramProfilePic hd_profile_pic_url_info) {
        this.hd_profile_pic_url_info = hd_profile_pic_url_info;
    }

    public String getExternal_lynx_url() {
        return external_lynx_url;
    }

    public void setExternal_lynx_url(String external_lynx_url) {
        this.external_lynx_url = external_lynx_url;
    }

    public int getFollowing_count() {
        return following_count;
    }

    public void setFollowing_count(int following_count) {
        this.following_count = following_count;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}