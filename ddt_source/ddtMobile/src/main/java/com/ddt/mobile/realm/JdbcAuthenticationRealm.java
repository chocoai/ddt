/*
 * @(#)JdbcAuthenticationRealm.java 2014-4-15
 *
 */
package com.ddt.mobile.realm;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.ddt.core.common.SessionVariable;
import com.ddt.core.constants.Constants;
import com.ddt.core.meta.Role;
import com.ddt.core.meta.User;
import com.ddt.core.service.UserService;
import com.ddt.core.utils.CollectionUtil;
import com.google.common.collect.Lists;


/**
 * JdbcAuthenticationRealm.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-4-15
 * @since      1.0
 */
public class JdbcAuthenticationRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	
	private List<String> defaultRole = Lists.newArrayList();
	
	/**
	 * 
	 * 当用户进行访问链接时的授权方法
	 * 
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        
        SessionVariable model = (SessionVariable) principals.getPrimaryPrincipal();
        
        long id = model.getUser().getId();
        
        //加载用户的组信息和资源信息
        List<Role> roleList = userService.getUserRoles(id);
        
        model.setRolesList(roleList);
        
        //添加用户拥有的role
        addRoles(info, roleList);
        
        SecurityUtils.getSubject().getSession().setAttribute(Constants.USER_SESSION_KEY, model);
        
        return info;
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        String username = usernamePasswordToken.getUsername();
        
        if (username == null) {
            throw new AccountException("用户名不能为空");
        }
        
        User user = userService.getWxUserByMobile(username);
        
        if (user == null) {
            throw new UnknownAccountException("用户不存在");
        }
        
        SessionVariable model = new SessionVariable(user);
        
        return new SimpleAuthenticationInfo(model, user.getPassword(), getName());
	}
	
	/**
	 * 通过组集合，将集合中的role字段内容解析后添加到SimpleAuthorizationInfo授权信息中
	 * 
	 * @param info SimpleAuthorizationInfo
	 * @param roleList 组集合
	 */
	private void addRoles(SimpleAuthorizationInfo info, List<Role> roleList) {
		
		//解析当前用户组中的role
        List<String> roles = CollectionUtil.extractToList(roleList, "name", true);
//        List<String> roles = getValue(temp,"roles\\[(.*?)\\]");
       
        //添加默认的roles到roels
        if (CollectionUtils.isNotEmpty(defaultRole)) {
        	CollectionUtils.addAll(roles, defaultRole.iterator());
        }
        
        //将当前用户拥有的roles设置到SimpleAuthorizationInfo中
        info.addRoles(roles);
		
	}

	public List<String> getDefaultRole() {
		return defaultRole;
	}

	public void setDefaultRole(List<String> defaultRole) {
		this.defaultRole = defaultRole;
	}

}
