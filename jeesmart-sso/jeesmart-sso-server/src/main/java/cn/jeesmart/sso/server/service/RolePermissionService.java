package cn.jeesmart.sso.server.service;

import cn.jeesmart.common.dao.mybatis.BaseDao;
import cn.jeesmart.sso.server.model.RolePermission;

import java.util.List;

/**
 * 角色权限映射服务接口
 * 
 * @author Joe
 */
public interface RolePermissionService extends BaseDao<RolePermission, String> {
    /**
     * 根据角色ID给角色授权
     * @param roleId 角色ID
     * @param list 角色权限映射集合
     * @return
     */
    public void allocate(String roleId, List<RolePermission> list);
}
