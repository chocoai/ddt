/*
 * @(#)UserService.java 2014-4-15
 *
 */
package com.ddt.core.service;

import java.util.List;

import com.ddt.core.meta.Role;
import com.ddt.core.meta.User;

/**
 * UserService.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-4-15
 * @since      1.0
 */
public interface UserService {

	User getUserByName(String username);

	List<Role> getUserRoles(long id);

	void insertUser(User user);

	void updateUser(User u);

}
