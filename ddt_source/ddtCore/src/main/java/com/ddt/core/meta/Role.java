package com.ddt.core.meta;


/**
 * 组实体
 * 
 * @author roy
 *
 */
public class Role {

	public static final String USERROLE = "userRole";
	
	private long id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 构造方法
	 */
	public Role() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 获取组名称
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置组名称
	 * 
	 * @param name 组名称
	 */
	public void setName(String name) {
		this.name = name;
	}
}
