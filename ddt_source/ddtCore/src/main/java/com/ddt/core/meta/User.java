/*
 * @(#)User.java 2014-4-15
 *
 */
package com.ddt.core.meta;


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
	 * 登录邮箱
	 */
	private String email;
	
	/**
	 * 登录密码
	 */
	private String password;
	
	/**
	 * 公司名称
	 */
	private String companyName;
	
	/**
	 * 联系人
	 */
	private String contacts;
	
	/**
	 * 手机号码
	 */
	private String phoneNumber;
	
	/**
	 * 固定电话
	 */
	private String telephone;
	
	/**
	 * 传真
	 */
	private String fax;
	
	/**
	 * 公司类型
	 */
	private int companyType;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * qq号
	 */
	private long qq;
	
	private int zipcode;
	
	private String homepage;
	
	
	/**
	 * 描述
	 */
	private String description;
	
	private int state;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public int getCompanyType() {
		return companyType;
	}

	public void setCompanyType(int companyType) {
		this.companyType = companyType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getQq() {
		return qq;
	}

	public void setQq(long qq) {
		this.qq = qq;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}