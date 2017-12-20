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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Joe
 */
@Validated
@RestController
@RequestMapping("/admin/app")
@Api(tags = "应用管理")
public class AppController {

	@Resource
	private AppService appService;

	@ApiOperation(value ="列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Result list(
			@ApiParam(value = "名称 ") @RequestParam("name") String name,
			@ApiParam(value = "开始页码", required = true) @RequestParam("pageNo") Integer pageNo,
			@ApiParam(value = "显示条数", required = true) @RequestParam("pageSize") Integer pageSize) {
		Map<String,Object> param = new HashMap<>();
		param.put("name",name);
		param.put("pageNo",(pageNo-1)*pageSize);
		param.put("pageSize",pageSize);
		return Result.createSuccessResult().setData(appService.findByKey(param,".findByKey"));
	}

	@ApiOperation(value ="验证应用编码")
	@RequestMapping(value = "/validateCode", method = RequestMethod.POST)
	public Result validateCode(
			@ApiParam(value = "id") Integer id,
			@ApiParam(value = "应用编码", required = true) @RequestParam("code") String code) {
		Result result = Result.createSuccessResult();
		Map<String,Object> param = new HashMap<>();
		param.put("code",code);
		App db = appService.findByParam(param,".findByCode");
		if (null != db && !db.getId().equals(id)) {
			result.setCode(ResultCode.ERROR).setMessage("应用编码已存在");
		}
		return result;
	}


	@ApiOperation(value ="新增/修改提交")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result save(
			@ApiParam(value = "id") @RequestParam("id") Integer id,
			@ApiParam(value = "名称", required = true)  @RequestParam("name") String name,
			@ApiParam(value = "应用编码", required = true) @RequestParam("code")  String code,
			@ApiParam(value = "是否启用", required = true) @RequestParam("isEnable")  Boolean isEnable,
			@ApiParam(value = "排序", required = true) @RequestParam("sort") Integer sort) {
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
}