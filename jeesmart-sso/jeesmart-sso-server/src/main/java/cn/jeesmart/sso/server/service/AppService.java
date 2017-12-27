package cn.jeesmart.sso.server.service;
import cn.jeesmart.common.dao.mybatis.BaseDao;
import cn.jeesmart.sso.server.model.App;

import java.util.Set;

/**
 * 应用服务接口
 * 
 * @author Joe
 */
public interface AppService extends BaseDao<App, String> {
    /**
     * 根据管理员ID查询已分配应用编码
     * @param isEnable
     * @param userId
     * @return
     */
    public Set<String> findAppCodeByUserId(Boolean isEnable, Integer userId);
}