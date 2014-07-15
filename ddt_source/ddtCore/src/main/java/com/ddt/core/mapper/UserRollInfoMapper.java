/*
 * @(#)UserRollInfoMapper.java 2014-7-3
 *
 */
package com.ddt.core.mapper;

import java.util.Map;

import com.ddt.core.meta.UserRollInfo;

/**
 * UserRollInfoMapper.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-7-3
 * @since      1.0
 */
public interface UserRollInfoMapper {

	UserRollInfo getUserRollInfoByIds(Map<String, Object> params);

	void addUserRollInfo(UserRollInfo userRollInfo);

	void updateUserRollInfo(UserRollInfo userRollInfo);

}
