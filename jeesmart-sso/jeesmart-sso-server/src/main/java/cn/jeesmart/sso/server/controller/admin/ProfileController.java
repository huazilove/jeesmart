package cn.jeesmart.sso.server.controller.admin;

import cn.jeesmart.common.constants.ReturnCode;
import cn.jeesmart.common.web.controller.BaseController;
import cn.jeesmart.common.web.provider.PasswordProvider;
import cn.jeesmart.sso.client.SessionUtils;
import cn.jeesmart.sso.server.common.LoginUser;
import cn.jeesmart.sso.server.common.TokenManager;
import cn.jeesmart.sso.server.model.User;
import cn.jeesmart.sso.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Joe
 */
@Api(tags = "个人中心")
@Controller
@RequestMapping("/admin/profile")
public class ProfileController extends BaseController {

	@Resource
	private TokenManager tokenManager;
	@Resource
	private UserService userService;

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model, HttpServletRequest request) {
		LoginUser loginUser = tokenManager.validate(SessionUtils.getSessionUser(request).getToken());
		if (loginUser != null) {
			model.addAttribute("user", userService.findById(loginUser.getUserId()));
		}
		return "/admin/profile";
	}

	@ApiOperation("修改密码提交")
	@RequestMapping(value = "/savePassword", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> save(
			@ApiParam(value = "新密码", required = true) @RequestParam("newPassword") String  newPassword,
			@ApiParam(value = "确认密码", required = true) @RequestParam("confirmPassword") String  confirmPassword,
			HttpServletRequest request) {
		LoginUser loginUser = tokenManager.validate(SessionUtils.getSessionUser(request).getToken());
		if (loginUser != null) {
			User user = userService.findById(loginUser.getUserId());
			user.setPassword(PasswordProvider.encrypt(newPassword));
			userService.update(user);
			return makeErrorMessage(ReturnCode.SUCCESS,"修改成功","修改成功");
		}
		else {
			return makeErrorMessage(ReturnCode.SUCCESS,"修改失败","修改失败");
		}
	}
}