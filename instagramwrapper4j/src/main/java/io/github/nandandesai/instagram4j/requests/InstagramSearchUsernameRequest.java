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

import io.github.nandandesai.instagram4j.requests.payload.InstagramSearchUsernameResult;

import java.io.IOException;

/**
 * Search Username Request
 * 
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class InstagramSearchUsernameRequest extends InstagramGetRequest<InstagramSearchUsernameResult> {

    private String username;

    public InstagramSearchUsernameRequest(String username) {
        this.username = username;
    }

    @Override
    public String getUrl() {
        return "users/" + username + "/usernameinfo/";
    }

    @Override
    public InstagramSearchUsernameResult parseResult(int statusCode, String content) throws IllegalAccessException, IOException, InstantiationException {
        return parseJson(statusCode, content, InstagramSearchUsernameResult.class);
    }

}
