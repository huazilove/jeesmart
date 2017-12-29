package cn.jeesmart.sso.server.service.impl;
import cn.jeesmart.common.dao.mybatis.AbstractBaseDao;
import cn.jeesmart.sso.server.model.UserRole;
import cn.jeesmart.sso.server.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Joe
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends AbstractBaseDao<UserRole, String> implements UserRoleService {
    @Override
    @Transactional
    public void allocate(String userId, String appId, List<UserRole> list) {
        String operate =".deleteByUserIds";
        Map<String,Object> param = new HashMap<>();
        param.put("appId",appId);
        param.put("userId",Arrays.asList(userId));
        super.delete(param,operate);
        super.batchSave(list);
    }
}
