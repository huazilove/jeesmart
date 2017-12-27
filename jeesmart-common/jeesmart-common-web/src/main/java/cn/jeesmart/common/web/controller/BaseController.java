package cn.jeesmart.common.web.controller;

import cn.jeesmart.common.constants.Message;
import cn.jeesmart.common.constants.ReturnCode;
import cn.jeesmart.common.exception.BusinessException;
import cn.jeesmart.common.utils.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.*;

/**
 * Controller基类
 *
 * @author Joe
 */
public abstract class BaseController {

    private static final String UNKNOWN = "unknown";

    private Integer[] getAjaxIds(final String str, final String separator) {
        Integer[] ids = null;
        if (str != null) {
            String[] strs = str.split(separator);
            ids = new Integer[strs.length];
            for (int i = 0, length = strs.length; i < length; i++) {
                ids[i] = Integer.valueOf(strs[i]);
            }
        }
        return ids;
    }

    protected List<Integer> getAjaxIds(final String ids) {
        return StringHelper.isBlank(ids) ? new ArrayList<Integer>(0) : Arrays.asList(getAjaxIds(ids, ","));
    }


    /**
     * 获取IP地址
     *
     * @param request
     * @return
     */
    protected String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringHelper.isBlank(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringHelper.isBlank(ip) && !UNKNOWN.equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     *
     * @param binder the binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new StringEditor());
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    /**
     * Handle business exception map.
     *
     * @param ex the ex
     * @return the map
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleBusinessException(BusinessException ex) {
        return makeErrorMessage(ReturnCode.INTERNAL_SERVER_ERROR, "Business Error", ex.getMessage());
    }

    /**
     * Handle constraint violation exception map.
     *
     * @param ex the ex
     * @return the map
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> list = BeanValidators.extractMessage(ex);
        return makeErrorMessage(ReturnCode.INVALID_FIELD,
                "Invalid Field", Collections3.convertToString(list, ";"));
    }

    /**
     * Make error message map.
     *
     * @param code  the code
     * @param error the error
     * @param desc  the desc
     * @return the map
     */
    protected Map<String, Object> makeErrorMessage(String code, String error, String desc, Object data) {
        Map<String, Object> message = new HashMap<>();
        message.put(Message.RETURN_FIELD_CODE, code);
        message.put(Message.RETURN_FIELD_ERROR, error);
        message.put(Message.RETURN_FIELD_ERROR_DESC, desc);
        message.put(Message.RETURN_FIELD_DATA, data);
        return message;
    }

    protected Map<String, Object> makeErrorMessage(String code, String error, String desc) {
        Map<String, Object> message = new HashMap<>();
        message.put(Message.RETURN_FIELD_CODE, code);
        message.put(Message.RETURN_FIELD_ERROR, error);
        message.put(Message.RETURN_FIELD_ERROR_DESC, desc);
        return message;
    }
}