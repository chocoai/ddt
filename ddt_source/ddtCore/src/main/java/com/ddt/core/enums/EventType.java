/*
 * @(#)EventType.java 2013-10-8
 *
 * Copyright 2013 mircobuy, Inc. All rights reserved.
 */
package com.ddt.core.enums;

/**
 * EventType.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2013-10-8
 * @since      1.0
 */
public enum EventType {
	
	SUBSCRIBE("subscribe"), UNSUBSCRIBE("unsubscribe"), CLICK("CLICK");
	
	private String type;
	
	EventType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
