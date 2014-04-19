/*
 * @(#)RollBookUser.java 2014-4-19
 *
 */
package com.ddt.core.meta;

import java.util.Date;

/**
 * RollBookUser.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-4-19
 * @since      1.0
 */
public class RollBookUser {
	
	private long bookId;
	
	private long userId;
	
	private Date rollTime;
	
	private String rollInfo;

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getRollTime() {
		return rollTime;
	}

	public void setRollTime(Date rollTime) {
		this.rollTime = rollTime;
	}

	public String getRollInfo() {
		return rollInfo;
	}

	public void setRollInfo(String rollInfo) {
		this.rollInfo = rollInfo;
	}
	
}
