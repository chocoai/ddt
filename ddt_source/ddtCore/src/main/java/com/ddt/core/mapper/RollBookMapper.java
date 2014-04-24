/*
 * @(#)RollBookMapper.java 2014-4-19
 *
 */
package com.ddt.core.mapper;

import java.util.List;
import java.util.Map;

import com.ddt.core.meta.RollBook;
import com.ddt.core.meta.RollInfo;
import com.ddt.core.meta.User;
import com.ddt.core.meta.UserRollInfo;

/**
 * RollBookMapper.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-4-19
 * @since      1.0
 */
public interface RollBookMapper {

	List<RollBook> getRollBookList(Map<String, Object> params);

	List<User> getRollBookUserList(Map<String, Object> params);

	List<RollInfo> getRollInfoList(Map<String, Object> params);

	List<UserRollInfo> getUserRollInfoList(Map<String, Object> params);

}
