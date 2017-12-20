package cn.jeesmart.sso.server.service.impl;


import cn.jeesmart.common.dao.mybatis.AbstractBaseDao;
import cn.jeesmart.sso.server.model.Role;
import cn.jeesmart.sso.server.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author Joe
 */
@Service("roleService")
public class RoleServiceImpl extends AbstractBaseDao<Role, Integer> implements RoleService {

}
