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
 * Search Users Result
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class InstagramSearchUsersResult extends StatusResult {
    private List<InstagramSearchUsersResultUser> users;
    private boolean has_more;
    private int num_results;

    public List<InstagramSearchUsersResultUser> getUsers() {
        return users;
    }

    public void setUsers(List<InstagramSearchUsersResultUser> users) {
        this.users = users;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public int getNum_results() {
        return num_results;
    }

    public void setNum_results(int num_results) {
        this.num_results = num_results;
    }
}
