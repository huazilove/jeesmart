package cn.jeesmart.sso.server.controller.admin;

import cn.jeesmart.common.constants.ReturnCode;
import cn.jeesmart.common.dao.mybatis.Pager;
import cn.jeesmart.common.validator.Validator;
import cn.jeesmart.common.validator.annotation.ValidateParam;
import cn.jeesmart.common.web.controller.BaseController;
import cn.jeesmart.sso.server.model.App;
import cn.jeesmart.sso.server.model.Role;
import cn.jeesmart.sso.server.model.RolePermission;
import cn.jeesmart.sso.server.service.AppService;
import cn.jeesmart.sso.server.service.RolePermissionService;
import cn.jeesmart.sso.server.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Joe
 */
@Api(tags = "角色管理")
@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController {

	@Resource
	private RoleService roleService;
	@Resource
	private AppService appService;
	@Resource
	private RolePermissionService rolePermissionService;

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		model.addAttribute("appList", getAppList());
		return "/admin/role";
	}

	@ApiOperation("新增/修改页")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@ApiParam(value = "id") String id, Model model) {
		Role role;
		if (id == null) {
			role = new Role();
		}
		else {
			role = roleService.findById(id);
		}
		model.addAttribute("role", role);
		model.addAttribute("appList", getAppList());
		return "/admin/roleEdit";
	}

	@ApiOperation("列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> list(
			@ApiParam(value = "角色名")String name,
			@ApiParam(value = "应用ID ") String appId,
			@ApiParam(value = "开始页码", required = true)  Integer pageNo,
			@ApiParam(value = "显示条数", required = true)  Integer pageSize) {
		/**
		 * 跟sql语句ID对应
		 */
		String operate = ".findByKey";
		Map<String,Object> param = new HashMap<>();
		param.put("name",name);
		param.put("appId",appId);
		param.put("pageNo",(pageNo-1)*pageSize);
		param.put("pageSize",pageSize);
		Pager<Role> pager = roleService.findByKey(param,operate);
		return makeErrorMessage(ReturnCode.SUCCESS,pager.getPageList());
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
		roleService.batchUpdate(param,getAjaxIds(ids),operate);
		return makeErrorMessage(ReturnCode.SUCCESS,"处理成功","处理成功");
	}

	@ApiOperation("新增/修改提交")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> save(
			@ApiParam(value = "id") String id,
			@ApiParam(value = "应用id", required = true) @ValidateParam({ Validator.NOT_BLANK }) String appId,
			@ApiParam(value = "角色名", required = true) @ValidateParam({ Validator.NOT_BLANK }) String name,
			@ApiParam(value = "排序", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer sort,
			@ApiParam(value = "描述") String description,
			@ApiParam(value = "是否启用", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		Role role;
		if (id == null) {
			role = new Role();
		}
		else {
			role = roleService.findById(id);
		}
		role.setAppId(appId);
		role.setName(name);
		role.setSort(sort);
		role.setDescription(description);
		role.setIsEnable(isEnable);
		roleService.save(role);
		return makeErrorMessage(ReturnCode.SUCCESS,"处理成功","处理成功");
	}
	
	@ApiOperation("角色权限对应关系数据")
	@RequestMapping(value = "/allocate", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> allocate(
			@ApiParam(value = "角色id", required = true) @ValidateParam({ Validator.NOT_BLANK }) String roleId) {
		/**
		 * 跟sql语句ID对应
		 */
		String operate = ".findByRoleId";
		Map<String,Object> param = new HashMap<>();
		param.put("roleId",roleId);
		return makeErrorMessage(ReturnCode.SUCCESS,rolePermissionService.find(param,operate));
	}
	
	@ApiOperation("角色授权提交")
	@RequestMapping(value = "/allocateSave", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> allocateSave(
			@ApiParam(value = "应用id", required = true) @ValidateParam({ Validator.NOT_BLANK }) String appId,
			@ApiParam(value = "角色id", required = true) @ValidateParam({ Validator.NOT_BLANK }) String roleId,
			@ApiParam(value = "权限ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String permissionIds) {
		List<String> idList = getAjaxIds(permissionIds);
		List<RolePermission> list = new ArrayList<RolePermission>();
		String permissionId;
		for (Iterator<String> i = idList.iterator(); i.hasNext(); list.add(new RolePermission(appId, roleId, permissionId))) {
			permissionId = i.next();
		}
		rolePermissionService.allocate(roleId, list);
		return makeErrorMessage(ReturnCode.SUCCESS,"处理成功","处理成功");
	}

	@ApiOperation("删除")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> delete(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids) {
		roleService.deleteById(getAjaxIds(ids));
		return makeErrorMessage(ReturnCode.SUCCESS,"处理成功","处理成功");
	}

	private List<App> getAppList() {
		return appService.findAll();
	}
}