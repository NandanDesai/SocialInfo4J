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
 * Status Result
 * @author Bruno Candido Volpato da Cunha
 *
 */
public class StatusResult {
    private String status;
    private String message;
    
    private boolean spam;
    private boolean lock;
    private String feedback_title;
    private String feedback_message;
    private String error_type;
    private String checkpoint_url;

    public StatusResult(){}

    public StatusResult(String status, String message, boolean spam, boolean lock, String feedback_title, String feedback_message, String error_type, String checkpoint_url) {
        this.status = status;
        this.message = message;
        this.spam = spam;
        this.lock = lock;
        this.feedback_title = feedback_title;
        this.feedback_message = feedback_message;
        this.error_type = error_type;
        this.checkpoint_url = checkpoint_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSpam() {
        return spam;
    }

    public void setSpam(boolean spam) {
        this.spam = spam;
    }

    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public String getFeedback_title() {
        return feedback_title;
    }

    public void setFeedback_title(String feedback_title) {
        this.feedback_title = feedback_title;
    }

    public String getFeedback_message() {
        return feedback_message;
    }

    public void setFeedback_message(String feedback_message) {
        this.feedback_message = feedback_message;
    }

    public String getError_type() {
        return error_type;
    }

    public void setError_type(String error_type) {
        this.error_type = error_type;
    }

    public String getCheckpoint_url() {
        return checkpoint_url;
    }

    public void setCheckpoint_url(String checkpoint_url) {
        this.checkpoint_url = checkpoint_url;
    }
}