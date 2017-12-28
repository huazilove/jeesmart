package cn.jeesmart.sso.server.service;
import cn.jeesmart.common.dao.mybatis.BaseDao;
import cn.jeesmart.sso.server.model.User;

import java.util.List;
import java.util.Map;


/**
 * 管理员服务接口
 * 
 * @author Joe
 */
public interface UserService extends BaseDao<User, String> {
    /**
     * 登录
     * @param ip
     * @param appCode
     * @param account
     * @param password
     * @return
     */
    public Map<String,Object> login(String ip, String appCode, String account, String password);
    /**
     * 通过主键集合删除实体
     * @param idList
     */
    public void deleteById(List<String> idList);
}
