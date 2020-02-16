/**
 * Copyright (C) 2018 Zsombor Gegesy (gzsombor@gmail.com)
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


/**
 * Representation of a location in a Feed, used in {@link InstagramFeedItem}
 */
public class FeedItemLocation {
    private long pk;
    private String name;
    private String address;
    private String city;
    private String short_name;
    private double lat;
    private double lng;
    private long facebook_places_id;
    private String external_source;

    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public long getFacebook_places_id() {
        return facebook_places_id;
    }

    public void setFacebook_places_id(long facebook_places_id) {
        this.facebook_places_id = facebook_places_id;
    }

    public String getExternal_source() {
        return external_source;
    }

    public void setExternal_source(String external_source) {
        this.external_source = external_source;
    }
}
