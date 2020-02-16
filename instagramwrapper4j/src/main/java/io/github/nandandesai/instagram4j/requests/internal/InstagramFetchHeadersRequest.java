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


import io.github.nandandesai.instagram4j.requests.InstagramGetRequest;
import io.github.nandandesai.instagram4j.requests.payload.StatusResult;
import io.github.nandandesai.instagram4j.utils.InstagramGenericUtil;

import java.io.IOException;

/**
 * Fetch Headers Request
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class InstagramFetchHeadersRequest extends InstagramGetRequest<StatusResult> {

    @Override
    public String getUrl() {
        return "si/fetch_headers/?challenge_type=signup&guid=" + InstagramGenericUtil.generateUuid(false);
    }

    @Override
    public String getPayload() {
        return null;
    }

    @Override
    public boolean requiresLogin() {
        return false;
    }

    @Override
    public StatusResult parseResult(int statusCode, String content) throws IOException, InstantiationException, IllegalAccessException {
        return parseJson(statusCode, content, StatusResult.class);
    }

}
