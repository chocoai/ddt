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
	private long id;
	
	private long bookId;
	
	/**
	 * 上传名单名字
	 */
	private String rollname;
	
	/**
	 * 上传名单的用户id
	 */
	private long userId;
	
	private Date rollTime;
	
	private String rollInfo;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getRollname() {
		return rollname;
	}

	public void setRollname(String rollname) {
		this.rollname = rollname;
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
