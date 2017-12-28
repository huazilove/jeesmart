package cn.jeesmart.sso.server.service.impl;


import cn.jeesmart.common.dao.mybatis.AbstractBaseDao;
import cn.jeesmart.sso.server.model.Role;
import cn.jeesmart.sso.server.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Joe
 */
@Service("roleService")
public class RoleServiceImpl extends AbstractBaseDao<Role, String> implements RoleService {
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RolePermissionService rolePermissionService;
    @Override
    @Transactional
    public void deleteById(List<String> idList) {
        rolePermissionService.batchDelete(idList);
        userRoleService.batchDelete(idList);
        batchDelete(idList);
    }
}
