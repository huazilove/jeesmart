package cn.jeesmart.sso.server.service.impl;
import javax.annotation.Resource;

import cn.jeesmart.common.utils.StringUtils;
import cn.jeesmart.sso.rpc.AuthenticationRpcService;
import cn.jeesmart.sso.rpc.RpcPermission;
import cn.jeesmart.sso.rpc.RpcUser;
import cn.jeesmart.sso.server.common.LoginUser;
import cn.jeesmart.sso.server.common.TokenManager;
import cn.jeesmart.sso.server.service.PermissionService;
import cn.jeesmart.sso.server.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Joe
 */
@Service("authenticationRpcService")
public class AuthenticationRpcServiceImpl implements AuthenticationRpcService {

	@Resource
	private PermissionService permissionService;
	@Resource
	private UserService userService;
	@Resource
	private TokenManager tokenManager;

	@Override
	public boolean validate(String token) {
		return tokenManager.validate(token) != null;
	}
	
	@Override
	public RpcUser findAuthInfo(String token) {
		LoginUser user = tokenManager.validate(token);
		if (user != null) {
			return new RpcUser(user.getAccount());
		}
		return null;
	}
	
	@Override
	public List<RpcPermission> findPermissionList(String token, String appCode) {
		if (StringUtils.isBlank(token)) {
			return null;//permissionService.findListById(appCode, null);
		}
		else {
			LoginUser user = tokenManager.validate(token);
			if (user != null) {
				return null;//permissionService.findListById(appCode, user.getUserId());
			}
			else {
				return new ArrayList<RpcPermission>(0);
			}
		}
	}
}
