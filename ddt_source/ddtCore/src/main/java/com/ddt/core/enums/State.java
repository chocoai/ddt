package com.ddt.core.enums;

/**
 * 状态枚举
 * 
 * @author roy
 *
 */
public enum State{
	
	Inactive(0, "未激活"),
	
	CERTI_NOT_SUBMIT(1, "未提交企业认证"),
	
	CERTI_SUBMIT(2, "已经提交企业认证"),
	
	CERTI_APPROVED(3, "认证通过"),
	
	CERTI_NOT_APPROVE(4, "认证未通过"),
	/**
	 * 禁用
	 */
	Disable(5,"禁用"),
	/**
	 * 删除
	 */
	Delete(6,"删除");
	
	//值
	private int value;
	//名称
	private String name;
	
	private State(int value,String name) {
		this.value = value;
		this.name = name;
	}

	/**
	 * 获取值
	 * @return Integer
	 */
	public int getValue() {
		return value;
	}

	/**
	 * 获取名称
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
}
