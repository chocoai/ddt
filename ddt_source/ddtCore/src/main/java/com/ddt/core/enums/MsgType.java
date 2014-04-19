/*
 * @(#)MsgType.java 2013-10-8
 *
 * Copyright 2013 mircobuy, Inc. All rights reserved.
 */
package com.ddt.core.enums;

/**
 * MsgType.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2013-10-8
 * @since      1.0
 */
public enum MsgType {
	TEXT("text"), IMAGE("image"), LOCATION("location"), LINK("link"), EVENT("event"), MUSIC("music"), NEWS("news");
	
	private String value;
	
	MsgType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
}
