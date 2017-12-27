package cn.jeesmart.sso.server.service.impl;
import cn.jeesmart.common.constants.Message;
import cn.jeesmart.common.constants.ReturnCode;
import cn.jeesmart.common.dao.mybatis.AbstractBaseDao;
import cn.jeesmart.common.model.enums.TrueFalseEnum;
import cn.jeesmart.sso.server.model.User;
import cn.jeesmart.sso.server.service.AppService;
import cn.jeesmart.sso.server.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * @author Joe
 */
@Service("userService")
public class UserServiceImpl extends AbstractBaseDao<User, String> implements UserService {
    /*@Resource
    private UserService userService;
    @Resource
    private AppService appService;
    @Override
    public Map<String,Object> login(String ip, String appCode, String account, String password) {
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> param = new HashMap<>();
        param.put("account",account);
        param.put("password",password);
        User user = userService.login(param);
        if (user == null) {
            map.put(Message.RETURN_FIELD_CODE,ReturnCode.INVALID_GRANT);
            map.put(Message.RETURN_FIELD_ERROR,"用户名或密码错误");
        }
        else if (TrueFalseEnum.FALSE.getValue().equals(user.getIsEnable())) {
            map.put(Message.RETURN_FIELD_CODE,ReturnCode.DISABLED_USER);
            map.put(Message.RETURN_FIELD_ERROR,"已被管理员禁用");
        }
        else {
            Set<String> set = null;//appService.findAppCodeByUserId(TrueFalseEnum.TRUE.getValue(), user.getId());
            if (CollectionUtils.isEmpty(set)) {
                map.put(Message.RETURN_FIELD_CODE,ReturnCode.ERROR);
                map.put(Message.RETURN_FIELD_ERROR,"不存在可操作应用");
            }
            else if (!set.contains(appCode)) {
                map.put(Message.RETURN_FIELD_CODE,ReturnCode.ERROR);
                map.put(Message.RETURN_FIELD_ERROR,"没有应用操作权限");
            }
            else {
                user.setLastLoginIp(ip);
                user.setLoginCount(user.getLoginCount() + 1);
                user.setLastLoginTime(new Date());
                userService.update(user);
                map.put(Message.RETURN_FIELD_DATA,user);
            }
        }
        return map;
    }*/
}
