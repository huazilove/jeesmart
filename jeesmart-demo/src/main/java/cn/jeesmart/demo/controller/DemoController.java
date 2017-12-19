package cn.jeesmart.demo.controller;
import cn.jeesmart.common.redis.RedisCache;
import cn.jeesmart.demo.entity.Student;
import cn.jeesmart.demo.entity.Teacher;
import cn.jeesmart.demo.service.IStudentService;
import cn.jeesmart.demo.service.ITeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *分别测试mybaits/hibernate
 *
 * @author wjh
 */
@Validated
@RestController
@RequestMapping("/demo")
@Api(tags = "接口管理")
public class DemoController{
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ITeacherService teacherService;
    @Resource
    private RedisCache redisCache;



    /**
     * 获取学生信息(mybaits)
     * @return
     */
    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @ApiOperation(value = "获取学生信息")
    public Student findById(
            @ApiParam(value = "主键ID")  @RequestParam("id") String id
    ){
       Student student = studentService.findById(Integer.parseInt(id));
       return student;
    }

    /**
     * 根据职称查找老师(hibernate)
     * @return
     */
    @RequestMapping(value = "/findByPositional", method = RequestMethod.POST)
    @ApiOperation(value = "根据职称查找老师")
    public List findByPositional(
            @ApiParam(value = "职称")  @RequestParam("positional") String positional
    ){
        Teacher teacher = new Teacher(1,"test","男","讲师");
        teacherService.saveOrUpdate(teacher);
        redisCache.set("test",positional);
        List list  = teacherService.findByPositional(positional);
        return list;
    }
}
