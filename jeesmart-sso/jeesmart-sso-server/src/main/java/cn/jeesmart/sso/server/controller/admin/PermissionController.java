package cn.jeesmart.sso.server.controller.admin;

import cn.jeesmart.common.constants.ReturnCode;
import cn.jeesmart.common.web.controller.BaseController;
import cn.jeesmart.sso.server.model.App;
import cn.jeesmart.sso.server.model.Permission;
import cn.jeesmart.sso.server.service.AppService;
import cn.jeesmart.sso.server.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Joe
 */
@Api(tags = "权限(含菜单)管理")
@Controller
@RequestMapping("/admin/permission")
public class PermissionController extends BaseController {

	@Resource
	private PermissionService permissionService;
	@Resource
	private AppService appService;

	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		model.addAttribute("appList", getAppList());
		return "/admin/permission";
	}

	@ApiOperation("权限树节点")
	@RequestMapping(value = "/nodes", method = RequestMethod.GET)
	public @ResponseBody
	List<Permission> nodes(
			@ApiParam(value = "应用id") String appId,
			@ApiParam(value = "名称") String name,
			@ApiParam(value = "是否启用 ") Boolean isEnable) {
		/**
		 * 跟sql语句ID对应
		 */
		String operate = ".findByName";
		Map<String,Object> param = new HashMap<>();
		param.put("name",name);
		param.put("appId",appId);
		param.put("isEnable",isEnable);
		List<Permission> list = permissionService.findAllByKey(param,operate);
		Permission permission = new Permission();
		permission.setId(null);
		permission.setParentId("-1");
		permission.setName("根节点");
		permission.setAppId(appId);
		list.add(0, permission);
		return list;
	}

	@ApiOperation("新增/修改提交")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> save(
			@ApiParam(value = "id") String id,
			@ApiParam(value = "应用id", required = true) @RequestParam("appId") String appId,
			@ApiParam(value = "父id", required = true) @RequestParam("parentId") String parentId,
			@ApiParam(value = "图标") String icon,
			@ApiParam(value = "名称", required = true) @RequestParam("name") String name,
			@ApiParam(value = "权限URL", required = true) @RequestParam("url") String url,
			@ApiParam(value = "排序", required = true) @RequestParam("sort") Integer sort,
			@ApiParam(value = "是否菜单", required = true) @RequestParam("isMenu") Boolean isMenu,
			@ApiParam(value = "是否启用", required = true) @RequestParam("isEnable") Boolean isEnable) {
		Permission permission;
		if (id == null) {
			permission = new Permission();
		}
		else {
			permission = permissionService.findById(id);
		}
		permission.setAppId(appId);
		permission.setParentId(parentId);
		permission.setIcon(icon);
		permission.setName(name);
		permission.setUrl(url);
		permission.setSort(sort);
		permission.setIsMenu(isMenu);
		permission.setIsEnable(isEnable);
		permissionService.save(permission);
		return makeErrorMessage(ReturnCode.SUCCESS,"保存成功","保存成功");
	}

	@ApiOperation("删除")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> delete(
			@ApiParam(value = "id", required = true) @RequestParam("appId") String id,
			@ApiParam(value = "应用id", required = true) @RequestParam("appId") String appId) {
		/**
		 * 跟sql语句ID对应
		 */
		String operate = ".deletePermission";
		Map<String,Object> param = new HashMap<>();
		param.put("id",id);
		param.put("appId",appId);
		permissionService.delete(param,operate);
		return makeErrorMessage(ReturnCode.SUCCESS,"删除成功","删除成功");
	}

	private List<App> getAppList() {
		return appService.findAll();
	}
}