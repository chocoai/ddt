/*
 * @(#)RollBookUser.java 2014-4-19
 *
 */
package com.ddt.core.meta;


/**
 * RollBookUser.java 点名册用户关系表
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-4-19
 * @since      1.0
 */
public class RollBookUser {
	
	private long bookId;
	
	/**
	 * 上传名单的用户id
	 */
	private long userId;
	

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
}
