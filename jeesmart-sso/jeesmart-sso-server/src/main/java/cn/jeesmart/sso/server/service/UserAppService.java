package cn.jeesmart.sso.server.service;

import cn.jeesmart.common.dao.mybatis.BaseDao;
import cn.jeesmart.sso.server.model.UserApp;

import java.util.List;

/**
 * 管理员角色映射服务接口
 * 
 * @author Joe
 */
public interface UserAppService extends BaseDao<UserApp, String> {
    /**
     * 根据管理员ID给管理员分配角色
     * @param userId 管理员ID
     * @param idList 应用ID集合
     * @param list 管理员角色映射集合
     * @return
     */
    public void allocate(String userId, List<String> idList, List<UserApp> list);
}
