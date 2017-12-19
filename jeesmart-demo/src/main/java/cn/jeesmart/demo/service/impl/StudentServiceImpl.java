package cn.jeesmart.demo.service.impl;

import cn.jeesmart.common.dao.mybatis.AbstractBaseDao;
import cn.jeesmart.demo.entity.Student;
import cn.jeesmart.demo.service.IStudentService;
import org.springframework.stereotype.Service;

/**
 * 学生服务实现类(mybatis)
 */
@Service
public class StudentServiceImpl extends AbstractBaseDao<Student, Integer> implements IStudentService {

}
