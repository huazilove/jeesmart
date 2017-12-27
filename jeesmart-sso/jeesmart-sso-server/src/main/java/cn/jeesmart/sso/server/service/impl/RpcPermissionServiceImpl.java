package cn.jeesmart.sso.server.service.impl;

import cn.jeesmart.common.dao.mybatis.AbstractBaseDao;
import cn.jeesmart.sso.rpc.RpcPermission;
import cn.jeesmart.sso.server.model.Permission;
import cn.jeesmart.sso.server.service.PermissionService;
import cn.jeesmart.sso.server.service.RpcPermissionService;
import org.springframework.stereotype.Service;

/**
 * @author Joe
 */
@Service("rpcPermissionService")
public class RpcPermissionServiceImpl extends AbstractBaseDao<RpcPermission, String> implements RpcPermissionService {

}
