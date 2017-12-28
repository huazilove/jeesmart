package cn.jeesmart.sso.server.service;

import cn.jeesmart.common.dao.mybatis.BaseDao;
import cn.jeesmart.sso.server.model.Role;

import java.util.List;

/**
 * 角色服务接口
 *
 * @author Joe
 */
public interface RoleService extends BaseDao<Role, String> {
    /**
     * 通过主键集合删除实体
     * @param idList
     */
    public void deleteById(List<String> idList);
}
