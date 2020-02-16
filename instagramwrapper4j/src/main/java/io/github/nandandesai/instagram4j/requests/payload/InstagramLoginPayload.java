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
 * Login Payload
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class InstagramLoginPayload {
    private String username;
    private String phone_id;
    private String _csrftoken;
    private String guid;
    private String device_id;
    private String password;
    private int login_attempt_account = 0;

    InstagramLoginPayload(){}

    public InstagramLoginPayload(String username, String phone_id, String _csrftoken, String guid, String device_id, String password, int login_attempt_account) {
        this.username = username;
        this.phone_id = phone_id;
        this._csrftoken = _csrftoken;
        this.guid = guid;
        this.device_id = device_id;
        this.password = password;
        this.login_attempt_account = login_attempt_account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(String phone_id) {
        this.phone_id = phone_id;
    }

    public String get_csrftoken() {
        return _csrftoken;
    }

    public void set_csrftoken(String _csrftoken) {
        this._csrftoken = _csrftoken;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLogin_attempt_account() {
        return login_attempt_account;
    }

    public void setLogin_attempt_account(int login_attempt_account) {
        this.login_attempt_account = login_attempt_account;
    }
}