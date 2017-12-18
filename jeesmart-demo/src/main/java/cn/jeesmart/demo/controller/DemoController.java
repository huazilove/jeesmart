package cn.jeesmart.demo.controller;
import cn.jeesmart.demo.entity.Student;
import cn.jeesmart.demo.service.IStudentService;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 *
 *
 * @author
 */
@Validated
@RestController
@RequestMapping("/demo")
@Api(tags = "接口管理")
public class DemoController{
    @Autowired
    private IStudentService studentService;



    /**
     * 批量获取
     * @return
     */
    @RequestMapping(value = "/findByName", method = RequestMethod.POST)
    @ApiOperation(value = "获取学生信息")
    public String findByName(
            @ApiParam(value = "姓名")  @RequestParam("name") String name
    ){
       Student student = studentService.findById(3);
       return JSON.toJSONString(student);
    }
}
