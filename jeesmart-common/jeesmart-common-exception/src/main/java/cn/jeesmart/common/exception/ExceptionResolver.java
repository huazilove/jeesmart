package cn.jeesmart.common.exception;

import cn.jeesmart.common.constants.Message;
import cn.jeesmart.common.constants.ReturnCode;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理
 * 
 * @author Joe
 */
public class ExceptionResolver implements HandlerExceptionResolver {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception exception) {
		Map<String,Object> map = new HashMap<>();
		if (exception instanceof SystemException) {
			SystemException ae = (SystemException) exception;
			map.put(Message.RETURN_FIELD_CODE,ae.getCode());
			map.put(Message.RETURN_FIELD_ERROR,ae.getMessage());
		}
		else {
			map.put(Message.RETURN_FIELD_CODE, ReturnCode.ERROR);
			map.put(Message.RETURN_FIELD_ERROR,"未知错误");
			LOGGER.error(exception.getMessage(), exception);
		}

		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpStatus.OK.value());
		try {
			PrintWriter writer = response.getWriter();
			writer.write(JSON.toJSONString(map));
			writer.flush();
			writer.close();
		}
		catch (IOException ie) {
			LOGGER.error("Failed to serialize the object to json for exception resolver!", ie);
		}
		return new ModelAndView();
	}
}
