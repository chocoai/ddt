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
	
	private Date rollStartTime;
	
	private Date rollEndTime;
	
	private String rollCode;
	
	private int rollUserCount;
	
	/**
	 * 纬度
	 */
	private String x;
	
	/**
	 * 经度
	 */
	private String y;

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


	public Date getRollStartTime() {
		return rollStartTime;
	}

	public void setRollStartTime(Date rollStartTime) {
		this.rollStartTime = rollStartTime;
	}

	public Date getRollEndTime() {
		return rollEndTime;
	}

	public void setRollEndTime(Date rollEndTime) {
		this.rollEndTime = rollEndTime;
	}

	public String getRollCode() {
		return rollCode;
	}

	public void setRollCode(String rollCode) {
		this.rollCode = rollCode;
	}

	public int getRollUserCount() {
		return rollUserCount;
	}

	public void setRollUserCount(int rollUserCount) {
		this.rollUserCount = rollUserCount;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}
}
