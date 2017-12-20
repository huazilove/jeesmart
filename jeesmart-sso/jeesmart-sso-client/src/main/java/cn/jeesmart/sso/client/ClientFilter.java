package cn.jeesmart.sso.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;


import cn.jeesmart.common.constants.Result;
import cn.jeesmart.common.exception.ServiceException;
import cn.jeesmart.common.utils.PropsUtil;
import cn.jeesmart.common.utils.SpringUtils;
import cn.jeesmart.common.utils.StringUtils;
import cn.jeesmart.sso.rpc.AuthenticationRpcService;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 单点登录权限系统Filter基类
 * 
 * @author Joe
 */
public abstract class ClientFilter implements Filter {

	/**
	 * 单点登录服务端URL
	 */
	protected String ssoServerUrl;
	private static final String SSO_SERVER_URL ="sso.server.url";
	private static final String SSO_APP_CODE ="sso.app.code";
	private static final String FILE_NAME ="sso.proerties";
	/**
	 * 当前应用关联权限系统的应用编码
	 */
	protected String ssoAppCode;
	/**
	 * 单点登录服务端提供的RPC服务，由Spring容器注入
	 */
	protected AuthenticationRpcService authenticationRpcService;

	/**
	 * 排除拦截
 	 */
	protected List<String> excludeList = null;
	
	private PathMatcher pathMatcher = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		if (StringUtils.isBlank(ssoServerUrl = PropsUtil.loadProps(FILE_NAME).getProperty(SSO_SERVER_URL))) {
			throw new IllegalArgumentException("ssoServerUrl不能为空");
		}
		if (StringUtils.isBlank(ssoAppCode =PropsUtil.loadProps(FILE_NAME).getProperty(SSO_APP_CODE))) {
			throw new IllegalArgumentException("ssoAppCode不能为空");
		}
		if ((authenticationRpcService = SpringUtils.getBean(AuthenticationRpcService.class)) == null) {
			throw new IllegalArgumentException("authenticationRpcService注入失败");
		}
		
		String excludes = filterConfig.getInitParameter("excludes");
		if (StringUtils.isNotBlank(excludes)) {
			excludeList = Arrays.asList(excludes.split(","));
			pathMatcher = new AntPathMatcher();
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (matchExcludePath(httpRequest.getServletPath())) {
			chain.doFilter(request, response);
		}else {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			try {
				doFilter(httpRequest, httpResponse, chain);
			}
			catch (ServiceException e) {
				httpResponse.setContentType("application/json;charset=UTF-8");
				httpResponse.setStatus(HttpStatus.OK.value());
				PrintWriter writer = httpResponse.getWriter();
				writer.write(JSON.toJSONString(Result.create(e.getCode()).setMessage(e.getMessage())));
				writer.flush();
				writer.close();
			}
		}
	}
	
	private boolean matchExcludePath(String path) {
		if (excludeList != null) {
			for (String ignore : excludeList) {
				if (pathMatcher.match(ignore, path)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 执行过滤器
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 * @throws ServiceException
	 */
	public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException, ServiceException;

	@Override
	public void destroy() {
	}
}