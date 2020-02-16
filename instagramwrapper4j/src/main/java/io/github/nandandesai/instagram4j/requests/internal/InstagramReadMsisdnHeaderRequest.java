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
package io.github.nandandesai.instagram4j.requests.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.nandandesai.instagram4j.requests.InstagramPostRequest;
import io.github.nandandesai.instagram4j.requests.payload.StatusResult;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Sync Features Request
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class InstagramReadMsisdnHeaderRequest extends InstagramPostRequest<StatusResult> {

    @Override
    public String getUrl() {
        return "accounts/read_msisdn_header/";
    }

    @Override
    public String getPayload() throws IOException, InstantiationException, IllegalAccessException {
        
        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("device_id", api.getUuid());
        likeMap.put("_csrftoken", api.getOrFetchCsrf());
        
        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(likeMap);

        return payloadJson;
    }

    @Override
    public StatusResult parseResult(int statusCode, String content) throws IOException, InstantiationException, IllegalAccessException {
        return parseJson(statusCode, content, StatusResult.class);
    }

    /**
     * @return if request must be logged in
     */
    @Override
    public boolean requiresLogin() {
        return false;
    }
}
