package cn.jeesmart.demo.service;

import cn.jeesmart.common.dao.hibernate.IBaseDao;
import cn.jeesmart.demo.entity.Teacher;

import java.util.List;

/**
 * 老师服务类接口(hibernate)
 *
 * @author
 */
public interface ITeacherService extends IBaseDao<Teacher> {
    /**
     * 根据职称查找老师
     *
     * @param value 职称
     * @return 该职称的学生集
     */
    public abstract List<Teacher> findByPositional(String value);
}