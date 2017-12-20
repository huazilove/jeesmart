package cn.jeesmart.sso.server.service.impl;
import cn.jeesmart.common.dao.mybatis.AbstractBaseDao;
import cn.jeesmart.sso.server.model.UserRole;
import cn.jeesmart.sso.server.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @author Joe
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends AbstractBaseDao<UserRole, Integer> implements UserRoleService {

}
