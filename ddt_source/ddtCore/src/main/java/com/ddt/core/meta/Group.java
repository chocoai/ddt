package com.ddt.core.meta;


/**
 * 组实体
 * 
 * @author roy
 *
 */
public class Group {

	public static final String UserGroups = "userGroups";
	
	private long id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 构造方法
	 */
	public Group() {
		
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
