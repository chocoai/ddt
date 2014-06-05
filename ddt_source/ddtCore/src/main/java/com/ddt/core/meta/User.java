/*
 * @(#)User.java 2014-4-15
 *
 */
package com.ddt.core.meta;

import java.util.Date;


/**
 * User.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-4-15
 * @since      1.0
 */
public class User {
	/**
	 * 主键id
	 */
	private long id;
	
	/**
	 * 微信名称
	 */
	private String wxName;
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 登录密码
	 */
	private String password;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	
	/**
	 * 描述
	 */
	private String description;
	
	private long groupId;
	
	private Date createTime;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWxName() {
		return wxName;
	}

	public void setWxName(String wxName) {
		this.wxName = wxName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}