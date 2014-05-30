/*
 * @(#)MusicMsg.java 2013-10-8
 *
 * Copyright 2013 mircobuy, Inc. All rights reserved.
 */
package com.ddt.mobile.msg;

/**
 * MusicMsg.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2013-10-8
 * @since      1.0
 */
public class MusicMsg extends BaseMsg {
	private String title;
	
	private String description;
	
	private String musicUrl;
	
	private String hqmusicUrl;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getHqmusicUrl() {
		return hqmusicUrl;
	}

	public void setHqmusicUrl(String hqmusicUrl) {
		this.hqmusicUrl = hqmusicUrl;
	}
}
