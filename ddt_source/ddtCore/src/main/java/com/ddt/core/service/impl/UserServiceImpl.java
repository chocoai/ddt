/*
 * @(#)UserServiceImpl.java 2014-4-15
 *
 */
package com.ddt.core.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddt.core.mapper.UserMapper;
import com.ddt.core.meta.Role;
import com.ddt.core.meta.RollBookUser;
import com.ddt.core.meta.User;
import com.ddt.core.service.UserService;

/**
 * UserServiceImpl.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-4-15
 * @since      1.0
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserByName(String username) {
		return userMapper.getUserByName(Collections.singletonMap("username", (Object) username));
	}

	@Override
	public List<Role> getUserRoles(long id) {
		return userMapper.getUserRoles(Collections.singletonMap("id", (Object) id));
	}

	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}

	@Override
	public void updateUser(User u) {
		userMapper.updateUser(u);
	}

	@Override
	public List<User> getRollBookUserList(long rollBookId, int limit, int offset) {
		Map<String, Object> params = new HashMap<>();
		
		params.put("rollBookId", rollBookId);
		params.put("limit", limit);
		params.put("offset", offset);
		return userMapper.getRollBookUserList(params);
	}

	@Override
	public void deleteUserById(long uid) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", uid);
		userMapper.deleteUserById(params);
	}

	@Override
	public void insertWxUser(User user) {
		userMapper.insertWxUser(user);
	}

	@Override
	public User getWxUserByName(String fromUserName) {
		return userMapper.getWxUserByName(Collections.singletonMap("username", (Object) fromUserName));
	}

	@Override
	public User getWxUserByMobile(String mobile) {
		return userMapper.getWxUserByMobile(Collections.singletonMap("mobile", (Object) mobile));
	}

	@Override
	public User getUserByWithNullWx(String userName) {
		return userMapper.getUserByWithNullWx(Collections.singletonMap("username", (Object) userName));
	}

	@Override
	public void updateWxUser(User u) {
		userMapper.updateWxUser(u);
	}

	@Override
	public RollBookUser getRollBookUser(long rollBookId, long userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("rollBookId", rollBookId);
		params.put("userId", userId);
		return userMapper.getRollBookUser(params);
	}

	@Override
	public void insertRollBookUser(RollBookUser rollBookUser) {
		userMapper.insertRollBookUser(rollBookUser);
	}

	@Override
	public void deleteRollBookUserById(long bookId, long userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("bookId", bookId);
		params.put("userId", userId);
		userMapper.deleteRollBookUserById(params);
	}

	@Override
	public User getUserByWxNumber(String wx) {
		Map<String, Object> params = new HashMap<>();
		params.put("wx", wx);
		return userMapper.getUserByWxNumber(params);
	}

	@Override
	public User getUserByNameAndInfoId(String userName, long infoId) {
		Map<String, Object> params = new HashMap<>();
		params.put("userName", userName);
		params.put("infoId", infoId);
		return userMapper.getUserByNameAndInfoId(params);
	}

	@Override
	public User getWxUserByWxNumber(String wx) {
		Map<String, Object> params = new HashMap<>();
		params.put("wx", wx);
		return userMapper.getWxUserByWxNumber(params);
	}

}
