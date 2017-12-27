package cn.jeesmart.sso.server.controller;

import cn.jeesmart.common.utils.CookieUtils;
import cn.jeesmart.common.utils.StringHelper;
import cn.jeesmart.sso.client.SessionUtils;
import cn.jeesmart.sso.server.common.TokenManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author Joe
 */
@Api(tags = "单点登出")
@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	@Resource
	private TokenManager tokenManager;

	@ApiOperation("登出")
	@RequestMapping(method = RequestMethod.GET)
	public String logout(@ApiParam(value = "返回链接") String backUrl, HttpServletRequest request) {
		String token = CookieUtils.getCookie(request, "token");
		if (StringHelper.isNotBlank(token)) {
			tokenManager.remove(token);
		}
		SessionUtils.invalidate(request);
		return "redirect:" + (StringHelper.isBlank(backUrl) ? "/admin/admin" : backUrl);
	}
}