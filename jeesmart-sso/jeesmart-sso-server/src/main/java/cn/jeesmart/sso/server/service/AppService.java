package cn.jeesmart.sso.server.service;
import cn.jeesmart.common.dao.mybatis.BaseDao;
import cn.jeesmart.sso.server.model.App;

import java.util.List;

/**
 * 应用服务接口
 * 
 * @author Joe
 */
public interface AppService extends BaseDao<App, String> {
    /**
     * 通过主键集合删除实体
     * @param idList
     */
    public void deleteById(List<String> idList);
}