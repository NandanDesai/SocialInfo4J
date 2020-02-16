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
 * @author Evgeny Bondarenko (evgbondarenko@gmail.com)
 *
 */
public class InstagramComment {
	private long pk;
	private long user_id;
	private String text;
	private int type;
	private long created_at;
	private long created_at_utc;
	private String content_type;
	private String status;
	private int bit_flags;
	private InstagramUser user;
	private boolean did_report_as_spam;
	private boolean share_enabled;
	private long media_id;
	private int comment_like_count;

	public long getPk() {
		return pk;
	}

	public void setPk(long pk) {
		this.pk = pk;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getCreated_at() {
		return created_at;
	}

	public void setCreated_at(long created_at) {
		this.created_at = created_at;
	}

	public long getCreated_at_utc() {
		return created_at_utc;
	}

	public void setCreated_at_utc(long created_at_utc) {
		this.created_at_utc = created_at_utc;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBit_flags() {
		return bit_flags;
	}

	public void setBit_flags(int bit_flags) {
		this.bit_flags = bit_flags;
	}

	public InstagramUser getUser() {
		return user;
	}

	public void setUser(InstagramUser user) {
		this.user = user;
	}

	public boolean isDid_report_as_spam() {
		return did_report_as_spam;
	}

	public void setDid_report_as_spam(boolean did_report_as_spam) {
		this.did_report_as_spam = did_report_as_spam;
	}

	public boolean isShare_enabled() {
		return share_enabled;
	}

	public void setShare_enabled(boolean share_enabled) {
		this.share_enabled = share_enabled;
	}

	public long getMedia_id() {
		return media_id;
	}

	public void setMedia_id(long media_id) {
		this.media_id = media_id;
	}

	public int getComment_like_count() {
		return comment_like_count;
	}

	public void setComment_like_count(int comment_like_count) {
		this.comment_like_count = comment_like_count;
	}
}
