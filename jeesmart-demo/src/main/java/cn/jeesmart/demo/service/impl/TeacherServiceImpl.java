package cn.jeesmart.demo.service.impl;

import cn.jeesmart.common.dao.hibernate.BaseDaoSupport;
import cn.jeesmart.demo.entity.Teacher;
import cn.jeesmart.demo.service.ITeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 老师服务实现类(hibernate)
 *
 * @author
 */
@Service
public class TeacherServiceImpl extends BaseDaoSupport<Teacher> implements ITeacherService {

    @Override
    public List findByPositional(String value) {
        return super.findByProperty("positional", value);
    }

}
