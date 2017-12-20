package cn.jeesmart.sso.server.service.impl;

import cn.jeesmart.common.dao.mybatis.AbstractBaseDao;
import cn.jeesmart.sso.server.model.Permission;
import cn.jeesmart.sso.server.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * @author Joe
 */
@Service("permissionService")
public class PermissionServiceImpl extends AbstractBaseDao<Permission, Integer> implements PermissionService {

}
