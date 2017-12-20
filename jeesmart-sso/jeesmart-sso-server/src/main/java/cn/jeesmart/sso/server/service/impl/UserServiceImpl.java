package cn.jeesmart.sso.server.service.impl;
import cn.jeesmart.common.dao.mybatis.AbstractBaseDao;
import cn.jeesmart.sso.server.model.User;
import cn.jeesmart.sso.server.service.UserService;
import org.springframework.stereotype.Service;


/**
 * @author Joe
 */
@Service("userService")
public class UserServiceImpl extends AbstractBaseDao<User, Integer> implements UserService {

}
