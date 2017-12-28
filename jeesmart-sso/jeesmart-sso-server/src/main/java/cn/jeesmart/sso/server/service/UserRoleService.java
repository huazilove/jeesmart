package cn.jeesmart.sso.server.service;

import cn.jeesmart.common.dao.mybatis.BaseDao;
import cn.jeesmart.sso.server.model.UserRole;

import java.util.List;

/**
 * 管理员角色映射服务接口
 * 
 * @author Joe
 */
public interface UserRoleService extends BaseDao<UserRole, String> {
    /**
     * 根据管理员ID给管理员分配角色
     * @param userId
     * @param appId
     * @param list
     */
    public void allocate(String userId, String appId, List<UserRole> list);
}
