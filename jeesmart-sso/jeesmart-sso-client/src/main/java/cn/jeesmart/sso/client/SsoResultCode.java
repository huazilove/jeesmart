package cn.jeesmart.sso.client;


import cn.jeesmart.common.constants.ResultCode;

/**
 * 单点登录权限返回码
 * 
 * @author Joe
 */
public class SsoResultCode extends ResultCode {
	/**
	 * SSO 用户授权出错
	 * TOKEN未授权或已过期
	 */
	public final static int SSO_TOKEN_ERROR = 1001;
	/**
	 * 没有访问权限
	 */
	public final static int SSO_PERMISSION_ERROR = 1002;
}
