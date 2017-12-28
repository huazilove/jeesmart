package cn.jeesmart.sso.server.controller.admin;

import cn.jeesmart.common.constants.ReturnCode;
import cn.jeesmart.common.model.enums.TrueFalseEnum;
import cn.jeesmart.common.validator.Validator;
import cn.jeesmart.common.validator.annotation.ValidateParam;
import cn.jeesmart.common.web.controller.BaseController;
import cn.jeesmart.sso.server.model.App;
import cn.jeesmart.sso.server.model.Role;
import cn.jeesmart.sso.server.model.UserRole;
import cn.jeesmart.sso.server.service.AppService;
import cn.jeesmart.sso.server.service.RoleService;
import cn.jeesmart.sso.server.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Joe
 */
@Api(tags = "管理员角色关系管理")
@Controller
@RequestMapping("/admin/userRole")
public class UserRoleController extends BaseController {

	@Resource
	private AppService appService;
	@Resource
	private RoleService roleService;
	@Resource
	private UserRoleService userRoleService;

	@ApiOperation("初始页")
	@RequestMapping(value = "/allocate", method = RequestMethod.GET)
	public String edit(
			@ApiParam(value = "管理员id", required = true) @ValidateParam({ Validator.NOT_BLANK }) String userId, Model model) {
		/**
		 * 跟sql语句ID对应
		 */
		String operate = ".findByUserId";
		Map<String,Object> param = new HashMap<>();
		param.put("isEnable",TrueFalseEnum.TRUE.getValue());
		param.put("userId",userId);
		List<App> appList = appService.findAllByKey(param,operate);
		model.addAttribute("userId", userId);
		model.addAttribute("appList", appList);
		model.addAttribute("roleList", getRoleList(userId, CollectionUtils.isEmpty(appList) ? null : appList.get(0).getId()));
		return "/admin/userRole";
	}
	
	@ApiOperation("管理员应用关系数据")
	@RequestMapping(value = "/change", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> changeApp(
			@ApiParam(value = "应用id", required = true) @ValidateParam({ Validator.NOT_BLANK }) String appId,
			@ApiParam(value = "管理员id", required = true) @ValidateParam({ Validator.NOT_BLANK }) String userId) {
		return makeErrorMessage(ReturnCode.SUCCESS,getRoleList(userId, appId));
	}

	@ApiOperation("管理员角色关联提交")
	@RequestMapping(value = "/allocateSave", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> allocateSave(
			@ApiParam(value = "应用id", required = true) @ValidateParam({ Validator.NOT_BLANK }) String appId,
			@ApiParam(value = "管理员id", required = true) @ValidateParam({ Validator.NOT_BLANK }) String userId,
			@ApiParam(value = "角色ids") String roleIds) {
		List<String> idList = getAjaxIds(roleIds);
		List<UserRole> list = new ArrayList<UserRole>();
		UserRole bean = null;
		for (String roleId : idList) {
			bean = new UserRole();
			bean.setAppId(appId);
			bean.setUserId(userId);
			bean.setRoleId(roleId);
			list.add(bean);
		}
		userRoleService.allocate(userId, appId, list);
		return makeErrorMessage(ReturnCode.SUCCESS,"授权成功","授权成功");
	}

	private List<Role> getRoleList(String userId, String appId) {
		String operate = ".findByAppId";
		Map<String,Object> param = new HashMap<>();
		param.put("isEnable",TrueFalseEnum.TRUE.getValue());
		param.put("appId",appId);
		List<Role> list = roleService.findAllByKey(param,operate);
		for (Role role : list) {
			operate = ".findByUserRoleId";
			param.put("roleId",role.getId());
			param.put("userId",userId);
			UserRole userRole = userRoleService.findByParam(param,operate);
			if (null != userRole) {
				role.setIsChecked(true);
			}
			else {
				role.setIsChecked(false);
			}
		}
		return list;
	}
}