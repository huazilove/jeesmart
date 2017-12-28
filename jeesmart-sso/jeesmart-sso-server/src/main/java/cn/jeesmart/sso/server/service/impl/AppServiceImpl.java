package cn.jeesmart.sso.server.service.impl;


import cn.jeesmart.common.dao.mybatis.AbstractBaseDao;
import cn.jeesmart.sso.server.model.App;
import cn.jeesmart.sso.server.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author Joe
 */
@Service
public class AppServiceImpl extends AbstractBaseDao<App, String> implements AppService {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private UserAppService userAppService;
    @Resource
    private RolePermissionService rolePermissionService;
    @Override
    @Transactional
    public void deleteById(List<String> idList) {
        rolePermissionService.batchDelete(idList);
        userRoleService.batchDelete(idList);
        userAppService.batchDelete(idList);
        permissionService.batchDelete(idList);
        roleService.batchDelete(idList);
        batchDelete(idList);
    }
}
