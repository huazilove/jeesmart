package cn.jeesmart.sso.server.service.impl;

import cn.jeesmart.common.dao.mybatis.AbstractBaseDao;
import cn.jeesmart.sso.server.model.UserApp;
import cn.jeesmart.sso.server.service.UserAppService;
import cn.jeesmart.sso.server.service.UserRoleService;
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
@Service("userAppService")
public class UserAppServiceImpl extends AbstractBaseDao<UserApp, String> implements UserAppService {
    @Resource
    private UserRoleService userRoleService;
    @Override
    @Transactional
    public void allocate(String userId, List<String> idList, List<UserApp> list) {
        String operate = ".deleteForChangeApp";
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("idList",idList);
        userRoleService.delete(map,operate);
        operate = ".deleteByUserIds";
        map.put("idList",Arrays.asList(userId));
        super.delete(map,operate);
        super.batchSave(list);
    }
}
