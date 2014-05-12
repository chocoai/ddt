/*
 * @(#)UserServiceImpl.java 2014-4-15
 *
 */
package com.ddt.core.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddt.core.mapper.UserMapper;
import com.ddt.core.meta.Role;
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

}
