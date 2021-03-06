package cn.jeesmart.sso.server.service;

import cn.jeesmart.common.dao.mybatis.BaseDao;
import cn.jeesmart.sso.rpc.RpcPermission;
import cn.jeesmart.sso.server.model.Permission;

/**
 * 权限服务接口
 *
 * @author Joe
 */
public interface RpcPermissionService extends BaseDao<RpcPermission, String> {

}
