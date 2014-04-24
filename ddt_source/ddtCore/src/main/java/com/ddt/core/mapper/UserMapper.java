/*
 * @(#)UserMapper.java 2014-4-15
 *
 */
package com.ddt.core.mapper;

import java.util.List;
import java.util.Map;

import com.ddt.core.meta.Group;
import com.ddt.core.meta.User;

/**
 * UserMapper.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-4-15
 * @since      1.0
 */
public interface UserMapper {

	User getUserByName(Map<String, Object> params);

	List<Group> getUserGroups(Map<String, Object> params);

	void insertUser(User user);
}
