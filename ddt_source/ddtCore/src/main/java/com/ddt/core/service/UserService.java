/*
 * @(#)UserService.java 2014-4-15
 *
 */
package com.ddt.core.service;

import java.util.List;

import com.ddt.core.meta.Role;
import com.ddt.core.meta.RollBookUser;
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

	List<User> getRollBookUserList(long groupId, int limit, int offset);

	void deleteUserById(long uid);

	void insertWxUser(User user);

	User getWxUserByName(String fromUserName);

	User getWxUserByMobile(String mobile);

	User getUserByWithNullWx(String userName);

	void updateWxUser(User u);

	RollBookUser getRollBookUser(long rollBookId, long id);

	void insertRollBookUser(RollBookUser rollBookUser);

	void deleteRollBookUserById(long bookId, long userId);

	User getUserByWxNumber(String wx);

	User getUserByNameAndInfoId(String userName, long infoId);

	User getWxUserByWxNumber(String wx);

}
