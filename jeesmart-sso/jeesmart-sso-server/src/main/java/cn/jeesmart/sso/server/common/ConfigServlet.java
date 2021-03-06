package cn.jeesmart.sso.server.common;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import cn.jeesmart.common.utils.config.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 初始化全局参数
 * 
 * @author Joe
 */
public class ConfigServlet extends HttpServlet {

	private static final long serialVersionUID = -7462526216386306510L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigServlet.class);
	@Override
	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		servletContext.setAttribute("_path", servletContext.getContextPath());
		try {
			servletContext.setAttribute("_staticPath", ConfigUtils.getProperty("static.url"));
			servletContext.setAttribute("_systemName", ConfigUtils.getProperty("system.name"));
			servletContext.setAttribute("_systemAdminName", ConfigUtils.getProperty("system.admin.name"));
			servletContext.setAttribute("_companyName", ConfigUtils.getProperty("system.company.name"));
			servletContext.setAttribute("_companyPhone", ConfigUtils.getProperty("system.company.phone"));
			servletContext.setAttribute("_copyRight", ConfigUtils.getProperty("system.copy.right"));
		}
		catch (Exception e) {
			LOGGER.error("系统初始化参数配置有误", e);
		}
	}
}
