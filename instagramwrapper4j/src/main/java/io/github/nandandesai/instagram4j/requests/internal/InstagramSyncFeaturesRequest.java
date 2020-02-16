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
import io.github.nandandesai.instagram4j.InstagramConstants;
import io.github.nandandesai.instagram4j.requests.InstagramPostRequest;
import io.github.nandandesai.instagram4j.requests.payload.InstagramSyncFeaturesResult;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Sync Features Request
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class InstagramSyncFeaturesRequest extends InstagramPostRequest<InstagramSyncFeaturesResult> {

    private boolean preLogin = false;

    public InstagramSyncFeaturesRequest(boolean preLogin) {
        this.preLogin = preLogin;
    }

    @Override
    public String getUrl() {
        return "qe/sync/";
    }

    @Override
    public String getPayload() throws IOException, InstantiationException, IllegalAccessException {
        
        Map<String, Object> likeMap = new LinkedHashMap<>();
        likeMap.put("id", api.getUuid());
        likeMap.put("experiments", InstagramConstants.DEVICE_EXPERIMENTS);
        
        if (!preLogin) {
            likeMap.put("_uuid", api.getUuid());
            likeMap.put("_uid", api.getUserId());
            likeMap.put("_csrftoken", api.getOrFetchCsrf());
            System.out.println("Prelogin csrftoken: "+api.getOrFetchCsrf());
            
        }
        
        ObjectMapper mapper = new ObjectMapper();
        String payloadJson = mapper.writeValueAsString(likeMap);

        return payloadJson;
    }

    @Override
    public InstagramSyncFeaturesResult parseResult(int statusCode, String content) throws IOException, InstantiationException, IllegalAccessException {
        return parseJson(statusCode, content, InstagramSyncFeaturesResult.class);
    }

    /**
     * @return if request must be logged in
     */
    @Override
    public boolean requiresLogin() {
        return false;
    }

}
