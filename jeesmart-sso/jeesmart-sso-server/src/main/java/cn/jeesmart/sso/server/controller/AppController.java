package cn.jeesmart.sso.server.controller;
import cn.jeesmart.common.constants.ReturnCode;
import cn.jeesmart.common.dao.mybatis.Pager;
import cn.jeesmart.common.utils.StringHelper;
import cn.jeesmart.common.web.controller.BaseController;
import cn.jeesmart.sso.server.model.App;
import cn.jeesmart.sso.server.service.AppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Joe
 */
@Validated
@RestController
@RequestMapping("/admin/app")
@Api(tags = "应用管理")
public class AppController extends BaseController{

	@Resource
	private AppService appService;

	@ApiOperation(value ="列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Map<String, Object> list(
			@ApiParam(value = "名称 ") @RequestParam("name") String name,
			@ApiParam(value = "开始页码", required = true) @RequestParam("pageNo") Integer pageNo,
			@ApiParam(value = "显示条数", required = true) @RequestParam("pageSize") Integer pageSize) {
		Map<String,Object> param = new HashMap<>();
		param.put("name",name);
		param.put("pageNo",(pageNo-1)*pageSize);
		param.put("pageSize",pageSize);
		Pager<App> pager = appService.findByKey(param,".findByKey");
		return makeErrorMessage(ReturnCode.SUCCESS,pager);
	}

	@ApiOperation(value ="验证应用编码")
	@RequestMapping(value = "/validateCode", method = RequestMethod.POST)
	public Map<String, Object> validateCode(
			@ApiParam(value = "id") Integer id,
			@ApiParam(value = "应用编码", required = true) @RequestParam("code") String code) {
		Map<String,Object> param = new HashMap<>();
		param.put("code",code);
		App db = appService.findByParam(param,".findByCode");
		if (null != db && !db.getId().equals(id)) {
			return makeErrorMessage(ReturnCode.ERROR,"应用编码已存在","应用编码已存在");
		}
		return makeErrorMessage(ReturnCode.SUCCESS,"","");
	}


	@ApiOperation(value ="新增/修改提交")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Map<String, Object> save(
			@ApiParam(value = "id") @RequestParam("id") String id,
			@ApiParam(value = "名称", required = true)  @RequestParam("name") String name,
			@ApiParam(value = "应用编码", required = true) @RequestParam("code")  String code,
			@ApiParam(value = "是否启用", required = true) @RequestParam("isEnable")  Boolean isEnable,
			@ApiParam(value = "排序", required = true) @RequestParam("sort") Integer sort) {
		App app = new App(name,code,sort);
		app.setIsEnable(isEnable);
		if(StringHelper.isBlank(id)){
			app.preInsert();
			appService.save(app);
		}else{
			app.setId(id);
			app.preUpdate();
			appService.update(app);
		}
		return makeErrorMessage(ReturnCode.SUCCESS,"保存成功","保存成功");
	}
}