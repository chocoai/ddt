/*
 * @(#)UserRollInfoService.java 2014-7-3
 *
 */
package com.ddt.core.service;

import com.ddt.core.meta.UserRollInfo;

/**
 * UserRollInfoService.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-7-3
 * @since      1.0
 */
public interface UserRollInfoService {

	UserRollInfo getUserRollInfoByIds(long infoId, long userId);

	void addUserRollInfo(UserRollInfo userRollInfo);

	void updateUserRollInfo(UserRollInfo userRollInfo);
}
