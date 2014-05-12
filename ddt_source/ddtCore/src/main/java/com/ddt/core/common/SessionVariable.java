package com.ddt.core.common;

import java.io.Serializable;
import java.util.List;

import com.ddt.core.meta.Role;
import com.ddt.core.meta.Resource;
import com.ddt.core.meta.User;

/**
 * 
 * 系统常用变量模型实体
 * 
 * @author roy
 * 
 */
@SuppressWarnings("serial")
public class SessionVariable implements Serializable {
	// 当前用户
	private User user;

	// 当前用户所在的组集合
	private List<Role> rolesList;

	// 当前用户的授权资源集合
	private List<Resource> authorizationInfo;

	// 当前用户的菜单集合
	private List<Resource> menusList;

	public SessionVariable() {

	}

	public SessionVariable(User user) {
		this.user = user;
	}

	public SessionVariable(User user, List<Role> groupsList,List<Resource> authorizationInfo, List<Resource> menusList) {
		this.user = user;
		this.rolesList = groupsList;
		this.authorizationInfo = authorizationInfo;
		this.menusList = menusList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 获取当前用户所在的组集合
	 * 
	 * @return List
	 */
	public List<Role> getRolesList() {
		return rolesList;
	}

	/**
	 * 设置当前用户所在的组集合
	 * 
	 * @param rolesList 组集合
	 */
	public void setRolesList(List<Role> rolesList) {
		this.rolesList = rolesList;
	}

	/**
	 * 获取当前用户的所有授权资源集合
	 * 
	 * @return List
	 */
	public List<Resource> getAuthorizationInfo() {
		return authorizationInfo;
	}

	/**
	 * 设置当前用户的所有授权资源集合
	 * 
	 * @param authorizationInfo 资源集合
	 */
	public void setAuthorizationInfo(List<Resource> authorizationInfo) {
		this.authorizationInfo = authorizationInfo;
	}

	/**
	 * 获取当前用户拥有的菜单集合
	 * 
	 * @return List
	 */
	public List<Resource> getMenusList() {
		return menusList;
	}

	/**
	 * 设置当前用户拥有的菜单集合
	 * 
	 * @param menusList 资源集合
	 */
	public void setMenusList(List<Resource> menusList) {
		this.menusList = menusList;
	}
}
