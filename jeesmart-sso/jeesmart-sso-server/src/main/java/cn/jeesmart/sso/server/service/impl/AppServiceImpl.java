package cn.jeesmart.sso.server.service.impl;


import cn.jeesmart.common.dao.mybatis.AbstractBaseDao;
import cn.jeesmart.sso.server.model.App;
import cn.jeesmart.sso.server.service.AppService;
import org.springframework.stereotype.Service;


/**
 * @author Joe
 */
@Service
public class AppServiceImpl extends AbstractBaseDao<App, String> implements AppService {

}
