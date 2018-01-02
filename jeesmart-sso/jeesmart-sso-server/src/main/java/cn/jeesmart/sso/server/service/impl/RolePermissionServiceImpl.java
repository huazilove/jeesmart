package cn.jeesmart.sso.server.service.impl;

import cn.jeesmart.common.dao.mybatis.AbstractBaseDao;
import cn.jeesmart.sso.server.model.RolePermission;
import cn.jeesmart.sso.server.service.AppService;
import cn.jeesmart.sso.server.service.PermissionJmsService;
import cn.jeesmart.sso.server.service.RolePermissionService;
import cn.jeesmart.sso.server.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Joe
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl extends AbstractBaseDao<RolePermission, String> implements RolePermissionService {
    @Resource
    private RoleService roleService;
    @Resource
    private AppService appService;
    @Resource
    private PermissionJmsService permissionJmsService;
    @Override
    @Transactional
    public void allocate(String roleId, List<RolePermission> list) {
        String operate = ".deleteByRoleIds";
        Map<String,Object> map = new HashMap<>();
        map.put("idList",Arrays.asList(roleId));
        super.delete(map,operate);
        super.batchSave(list);
        // JMS通知权限变更9
        permissionJmsService.send(appService.findById(roleService.findById(roleId).getAppId()).getCode());
    }
}
