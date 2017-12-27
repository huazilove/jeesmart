package cn.jeesmart.common.exception;

/**
 * 业务异常.
 *
 * @author wjh
 */
public class BusinessException extends Exception {

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

}