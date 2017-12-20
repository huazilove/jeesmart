package cn.jeesmart.sso.server.service.impl;

import cn.jeesmart.common.dao.mybatis.AbstractBaseDao;
import cn.jeesmart.sso.server.model.RolePermission;
import cn.jeesmart.sso.server.service.RolePermissionService;
import org.springframework.stereotype.Service;

/**
 * @author Joe
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl extends AbstractBaseDao<RolePermission, Integer> implements RolePermissionService {

}
