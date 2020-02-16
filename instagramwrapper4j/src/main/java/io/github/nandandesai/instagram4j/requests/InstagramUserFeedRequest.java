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
package io.github.nandandesai.instagram4j.requests;


import io.github.nandandesai.instagram4j.requests.payload.InstagramFeedResult;

import java.io.IOException;

/**
 * User Feed Request
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class InstagramUserFeedRequest extends InstagramGetRequest<InstagramFeedResult> {

    private long userId;
    private String maxId;
    private long minTimestamp;
    private long maxTimestamp;

    public InstagramUserFeedRequest(long userId) {
        this.userId = userId;
    }

    public InstagramUserFeedRequest(long userId, String maxId, long minTimestamp, long maxTimestamp) {
        this.userId = userId;
        this.maxId = maxId;
        this.minTimestamp = minTimestamp;
        this.maxTimestamp = maxTimestamp;
    }

    @Override
    public String getUrl() {
        return "feed/user/" + userId + "/?max_id=" + maxId + "&min_timestamp=" + minTimestamp + "&max_timestamp=" + maxTimestamp + "&rank_token=" + api.getRankToken() + "&ranked_content=true&";
    }

    @Override
    public InstagramFeedResult parseResult(int statusCode, String content) throws IllegalAccessException, IOException, InstantiationException {
        return parseJson(statusCode, content, InstagramFeedResult.class);
    }

}
