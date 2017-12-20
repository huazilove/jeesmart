package cn.jeesmart.sso.server.service.impl;

import cn.jeesmart.common.dao.mybatis.AbstractBaseDao;
import cn.jeesmart.sso.server.model.UserApp;
import cn.jeesmart.sso.server.service.UserAppService;
import org.springframework.stereotype.Service;


/**
 * @author Joe
 */
@Service("userAppService")
public class UserAppServiceImpl extends AbstractBaseDao<UserApp, Integer> implements UserAppService {

}
