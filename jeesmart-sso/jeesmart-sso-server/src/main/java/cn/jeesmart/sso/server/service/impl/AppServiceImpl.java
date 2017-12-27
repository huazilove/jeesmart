package cn.jeesmart.sso.server.service.impl;


import cn.jeesmart.common.dao.mybatis.AbstractBaseDao;
import cn.jeesmart.common.model.enums.TrueFalseEnum;
import cn.jeesmart.sso.server.model.App;
import cn.jeesmart.sso.server.service.AppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * @author Joe
 */
@Service
public class AppServiceImpl extends AbstractBaseDao<App, String> implements AppService {
    @Resource
    private AppService appService;
    @Override
    public Set<String> findAppCodeByUserId(Boolean isEnable, Integer userId) {
        Map<String,Object> param = new HashMap<>();
        param.put("userId",userId);
        param.put("isEnable",isEnable);
        return appService.findAppCodeByUserId(isEnable, userId);
    }
}
