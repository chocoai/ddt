/*
 * @(#)UserRollInfoServiceImpl.java 2014-7-3
 *
 */
package com.ddt.core.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddt.core.mapper.UserRollInfoMapper;
import com.ddt.core.meta.UserRollInfo;
import com.ddt.core.service.UserRollInfoService;

/**
 * UserRollInfoServiceImpl.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-7-3
 * @since      1.0
 */

@Service
public class UserRollInfoServiceImpl implements UserRollInfoService {
	
	@Autowired
	private UserRollInfoMapper userRollInfoMapper;
	
	@Override
	public UserRollInfo getUserRollInfoByIds(long infoId, long userId) {
		Map<String, Object> params = new HashMap<>();
		
		params.put("infoId", infoId);
		params.put("userId", userId);
		
		return userRollInfoMapper.getUserRollInfoByIds(params);
	}

	@Override
	public void addUserRollInfo(UserRollInfo userRollInfo) {
		userRollInfoMapper.addUserRollInfo(userRollInfo);
	}

	@Override
	public void updateUserRollInfo(UserRollInfo userRollInfo) {
		userRollInfoMapper.updateUserRollInfo(userRollInfo);
	}

}
