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
 * Data class for response from feed requests
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class InstagramFeedResult extends StatusResult {

    private boolean auto_load_more_enabled;
    private int num_results;
    private String next_max_id;

    private List<InstagramFeedItem> items;
    private List<InstagramFeedItem> ranked_items;

    private boolean more_available;

    public boolean isAuto_load_more_enabled() {
        return auto_load_more_enabled;
    }

    public void setAuto_load_more_enabled(boolean auto_load_more_enabled) {
        this.auto_load_more_enabled = auto_load_more_enabled;
    }

    public int getNum_results() {
        return num_results;
    }

    public void setNum_results(int num_results) {
        this.num_results = num_results;
    }

    public String getNext_max_id() {
        return next_max_id;
    }

    public void setNext_max_id(String next_max_id) {
        this.next_max_id = next_max_id;
    }

    public List<InstagramFeedItem> getItems() {
        return items;
    }

    public void setItems(List<InstagramFeedItem> items) {
        this.items = items;
    }

    public List<InstagramFeedItem> getRanked_items() {
        return ranked_items;
    }

    public void setRanked_items(List<InstagramFeedItem> ranked_items) {
        this.ranked_items = ranked_items;
    }

    public boolean isMore_available() {
        return more_available;
    }

    public void setMore_available(boolean more_available) {
        this.more_available = more_available;
    }
}
