/*
 * @(#)NewsMsg.java 2013-10-8
 *
 * Copyright 2013 mircobuy, Inc. All rights reserved.
 */
package com.ddt.mobile.msg;

import java.util.List;

/**
 * NewsMsg.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2013-10-8
 * @since      1.0
 */
public class NewsMsg extends BaseMsg {
	private List<Article> items;

	public List<Article> getItems() {
		return items;
	}

	public void setItems(List<Article> items) {
		this.items = items;
	}
}
