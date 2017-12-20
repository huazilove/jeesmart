package cn.jeesmart.sso.server.controller;

import cn.jeesmart.common.constants.Result;
import cn.jeesmart.common.constants.ResultCode;
import cn.jeesmart.common.validator.Validator;
import cn.jeesmart.common.validator.annotation.ValidateParam;
import cn.jeesmart.common.web.controller.BaseController;
import cn.jeesmart.sso.server.model.App;
import cn.jeesmart.sso.server.service.AppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Joe
 */
@Api(tags = "应用管理")
@Controller
@RequestMapping("/admin/app")
public class AppController extends BaseController {

	@Resource
	private AppService appService;

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute() {
		return "/admin/app";
	}

	@ApiOperation("新增/修改页")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@ApiParam(value = "id") Integer id, Model model) {
		App app;
		if (id == null) {
			app = new App();
		}
		else {
			app = appService.findById(id);
		}
		model.addAttribute("app", app);
		return "/admin/appEdit";
	}

	@ApiOperation("列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	Result list(
			@ApiParam(value = "名称 ") String name,
			@ApiParam(value = "开始页码", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageNo,
			@ApiParam(value = "显示条数", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageSize) {
		Map<String,Object> param = new HashMap<>();
		param.put("name",name);
		param.put("pageNo",pageNo);
		param.put("pageSize",pageSize);
		return Result.createSuccessResult().setData(appService.findByKey(param,".findByKey"));
	}

	@ApiOperation("验证应用编码")
	@RequestMapping(value = "/validateCode", method = RequestMethod.POST)
	public @ResponseBody Result validateCode(
			@ApiParam(value = "id") Integer id,
			@ApiParam(value = "应用编码", required = true) @ValidateParam({ Validator.NOT_BLANK }) String code) {
		Result result = Result.createSuccessResult();
		Map<String,Object> param = new HashMap<>();
		param.put("code",code);
		App db = appService.findByParam(param,".findByCode");
		if (null != db && !db.getId().equals(id)) {
			result.setCode(ResultCode.ERROR).setMessage("应用编码已存在");
		}
		return result;
	}

	/*@ApiOperation("启用/禁用")
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public @ResponseBody Result enable(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids,
			@ApiParam(value = "是否启用", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		appService.enable(isEnable, getAjaxIds(ids));
		return Result.createSuccessResult();
	}*/

	@ApiOperation("新增/修改提交")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Result save(
			@ApiParam(value = "id") Integer id,
			@ApiParam(value = "名称", required = true) @ValidateParam({ Validator.NOT_BLANK }) String name,
			@ApiParam(value = "应用编码", required = true) @ValidateParam({ Validator.NOT_BLANK }) String code,
			@ApiParam(value = "是否启用", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable,
			@ApiParam(value = "排序", required = true) @ValidateParam({ Validator.NOT_BLANK, Validator.INT }) Integer sort) {
		App app;
		if (id == null) {
			app = new App();
			app.setCreateTime(new Date());
		}
		else {
			app = appService.findById(id);
		}
		app.setName(name);
		app.setSort(sort);
		app.setIsEnable(isEnable);
		app.setCode(code);
		appService.save(app);
		return Result.createSuccessResult();
	}

	/*@ApiOperation("删除")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Result delete(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids) {
		appService.deleteById(getAjaxIds(ids));
		return Result.createSuccessResult();
	}*/
}