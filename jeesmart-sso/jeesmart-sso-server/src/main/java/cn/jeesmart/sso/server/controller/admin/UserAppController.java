package cn.jeesmart.sso.server.controller.admin;

import cn.jeesmart.common.constants.ReturnCode;
import cn.jeesmart.common.validator.Validator;
import cn.jeesmart.common.validator.annotation.ValidateParam;
import cn.jeesmart.common.web.controller.BaseController;
import cn.jeesmart.sso.server.model.App;
import cn.jeesmart.sso.server.model.UserApp;
import cn.jeesmart.sso.server.service.AppService;
import cn.jeesmart.sso.server.service.UserAppService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @author Joe
 */
@Api(tags = "管理员应用关系管理")
@Controller
@RequestMapping("/admin/userApp")
public class UserAppController extends BaseController {

	@Resource
	private AppService appService;
	@Resource
	private UserAppService userAppService;
	
	@ApiOperation("初始页")
	@RequestMapping(value = "/allocate", method = RequestMethod.GET)
	public String edit(
			@ApiParam(value = "管理员id", required = true) @ValidateParam({ Validator.NOT_BLANK }) String userId, Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("appList", getAppList(userId));
		return "/admin/userApp";
	}

	@ApiOperation("管理员应用关联提交")
	@RequestMapping(value = "/allocateSave", method = RequestMethod.POST)
	public @ResponseBody
	Map<String,Object> allocateSave(
			@ApiParam(value = "管理员id", required = true) @ValidateParam({ Validator.NOT_BLANK }) String userId,
			@ApiParam(value = "应用ids") String appIds) {
		List<String> idList = getAjaxIds(appIds);
		List<UserApp> list = new ArrayList<UserApp>();
		UserApp bean = null;
		for (String appId : idList) {
			bean = new UserApp();
			bean.setAppId(appId);
			bean.setUserId(userId);
			list.add(bean);
		}
		userAppService.allocate(userId, idList, list);
		return makeErrorMessage(ReturnCode.SUCCESS,"授权成功","授权成功");
	}
	
	private List<App> getAppList(String userId) {
		String operate = ".findByUserAppId";
		Map<String,Object> map = new HashMap<>();
		List<App> list = appService.findAll();
		for (App app : list) {
			map.put("userId",userId);
			map.put("appId",app.getId());
			UserApp userApp = userAppService.findByParam(map,operate);
			if (null != userApp) {
				app.setIsChecked(true);
			}
			else {
				app.setIsChecked(false);
			}
		}
		return list;
	}
}