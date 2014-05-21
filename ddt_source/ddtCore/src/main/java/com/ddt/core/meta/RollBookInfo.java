/*
 * @(#)RollInfo.java 2014-4-19
 *
 */
package com.ddt.core.meta;

import java.util.Date;

/**
 * RollInfo.java 点名册的点名情况
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-4-19
 * @since      1.0
 */
public class RollBookInfo {
	
	private long id;
	
	private long userId;
	
	private long rollBookId;
	
	private Date rollTime;
	
	private int rollUserCount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getRollBookId() {
		return rollBookId;
	}

	public void setRollBookId(long rollBookId) {
		this.rollBookId = rollBookId;
	}

	public Date getRollTime() {
		return rollTime;
	}

	public void setRollTime(Date rollTime) {
		this.rollTime = rollTime;
	}

	public int getRollUserCount() {
		return rollUserCount;
	}

	public void setRollUserCount(int rollUserCount) {
		this.rollUserCount = rollUserCount;
	}
}
