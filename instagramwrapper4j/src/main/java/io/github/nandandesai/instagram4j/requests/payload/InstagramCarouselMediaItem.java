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


/**
 * InstagramCarouselMediaItem
 * 
 * @author Justin Vo
 *
 */

public class InstagramCarouselMediaItem extends InstagramFeedItem {
//    private String id;
//    private String media_type;
//    private int original_width;
//    private int original_height;
//    private long pk;
    private String carousel_parent_id;

    public String getCarousel_parent_id() {
        return carousel_parent_id;
    }

    public void setCarousel_parent_id(String carousel_parent_id) {
        this.carousel_parent_id = carousel_parent_id;
    }
}
