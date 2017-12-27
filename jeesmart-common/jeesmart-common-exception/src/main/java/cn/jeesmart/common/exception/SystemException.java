package cn.jeesmart.common.exception;

import cn.jeesmart.common.constants.ReturnCode;

/**
 * 系统业务异常.
 *
 * @author wjh
 */
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -2678203134198782909L;

    public static final String MESSAGE = "应用异常";

    protected String code = ReturnCode.INTERNAL_SERVER_ERROR;

    public SystemException() {
        super(MESSAGE);
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}