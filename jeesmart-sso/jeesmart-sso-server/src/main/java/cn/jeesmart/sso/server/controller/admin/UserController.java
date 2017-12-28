package cn.jeesmart.sso.server.controller.admin;

import cn.jeesmart.common.constants.ReturnCode;
import cn.jeesmart.common.dao.mybatis.Pager;
import cn.jeesmart.common.exception.SystemException;
import cn.jeesmart.common.utils.StringHelper;
import cn.jeesmart.common.utils.config.ConfigUtils;
import cn.jeesmart.common.validator.Validator;
import cn.jeesmart.common.validator.annotation.ValidateParam;
import cn.jeesmart.common.web.controller.BaseController;
import cn.jeesmart.common.web.provider.PasswordProvider;
import cn.jeesmart.sso.server.model.App;
import cn.jeesmart.sso.server.model.User;
import cn.jeesmart.sso.server.service.AppService;
import cn.jeesmart.sso.server.service.RoleService;
import cn.jeesmart.sso.server.service.UserRoleService;
import cn.jeesmart.sso.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Joe
 */
@Api(tags = "管理员管理")
@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController {

	@Resource
	private UserService userService;
	@Resource
	private AppService appService;
	@Resource
	private RoleService roleService;
	@Resource
	private UserRoleService userRoleService;

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		model.addAttribute("appList", getAppList());
		return "/admin/user";
	}

	@ApiOperation("新增/修改页")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@ApiParam(value = "id") String id, Model model) {
		User user;
		if (id == null) {
			user = new User();
		}
		else {
			user = userService.findById(id);
		}
		model.addAttribute("user", user);
		model.addAttribute("appList", getAppList());
		return "/admin/userEdit";
	}

	@ApiOperation("列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	Map<String,Object> list(
			@ApiParam(value = "登录名") String account,
			@ApiParam(value = "应用id") String appId,
			@ApiParam(value = "开始页码", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageNo,
			@ApiParam(value = "显示条数", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageSize) {
		/**
		 * 跟sql语句ID对应
		 */
		String operate = ".findByKey";
		Map<String,Object> param = new HashMap<>();
		param.put("account",account);
		param.put("appId",appId);
		param.put("pageNo",(pageNo-1)*pageSize);
		param.put("pageSize",pageSize);
		Pager<User> pager = userService.findByKey(param,operate);
		return makeErrorMessage(ReturnCode.SUCCESS,pager.getPageList());
	}

	@ApiOperation("验证登录名")
	@RequestMapping(value = "/validateAccount", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> validateAccount(
			@ApiParam(value = "id") String id,
			@ApiParam(value = "登录名", required = true) @ValidateParam({ Validator.NOT_BLANK }) String account) {
		/**
		 * 跟sql语句ID对应
		 */
		String operate = ".findByAccount";
		Map<String,Object> param = new HashMap<>();
		param.put("account",account);
		User user = userService.findByParam(param,operate);
		if (null != user && !user.getId().equals(id)) {
			return makeErrorMessage(ReturnCode.USER_EXIST,"登录名已存在","登录名已存在");
		}
		return makeErrorMessage(ReturnCode.SUCCESS,"处理成功","处理成功");
	}

	@ApiOperation("启用/禁用")
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> enable(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids,
			@ApiParam(value = "是否启用", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		/**
		 * 跟sql语句ID对应
		 */
		String operate = ".enable";
		Map<String,Object> param = new HashMap<>();
		param.put("isEnable",isEnable);
		param.put("idList",getAjaxIds(ids));
		userService.batchUpdate(param,getAjaxIds(ids),operate);
		return makeErrorMessage(ReturnCode.SUCCESS,"处理成功","处理成功");
	}

	@ApiOperation("新增/修改提交")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> save(
			@ApiParam(value = "id") String id,
			@ApiParam(value = "登录名", required = true) @ValidateParam({ Validator.NOT_BLANK }) String account,
			@ApiParam(value = "密码 ") String password,
			@ApiParam(value = "是否启用", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		User user;
		if (id == null) {
			if (StringHelper.isBlank(password)) {
				throw new SystemException("密码不能为空");
			}
			user = new User();
			user.setCreateDate(new Date());
		}
		else {
			user = userService.findById(id);
		}
		user.setAccount(account);
		if (StringHelper.isNotBlank(password)) {
			user.setPassword(PasswordProvider.encrypt(password));
		}
		user.setIsEnable(isEnable);
		userService.save(user);
		return makeErrorMessage(ReturnCode.SUCCESS,"保存成功","保存成功");
	}

	@ApiOperation("重置密码")
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> resetPassword(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids) {
		/**
		 * 跟sql语句ID对应
		 */
		String operate = ".resetPassword";
		Map<String,Object> param = new HashMap<>();
		param.put("password",PasswordProvider.encrypt(ConfigUtils.getProperty("system.init.password")));
		param.put("idList",getAjaxIds(ids));
		userService.batchUpdate(param,getAjaxIds(ids),operate);
		return makeErrorMessage(ReturnCode.SUCCESS,"重置成功","重置成功");
	}

	@ApiOperation("删除")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> delete(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids) {
		userService.deleteById(getAjaxIds(ids));
		return makeErrorMessage(ReturnCode.SUCCESS,"删除成功","删除成功");
	}

	private List<App> getAppList() {
		return appService.findAll();
	}
}