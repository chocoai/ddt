/*
 * @(#)UserRollInfo.java 2014-4-24
 *
 */
package com.ddt.core.meta;

/**
 * UserRollInfo.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-4-24
 * @since      1.0
 */
public class UserRollInfo {
	private long id;
	
	private long rollInfoId;
	
	private long userId;
	
	private String info;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRollInfoId() {
		return rollInfoId;
	}

	public void setRollInfoId(long rollInfoId) {
		this.rollInfoId = rollInfoId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
