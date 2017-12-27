package cn.jeesmart.sso.server.model;

import java.util.Date;

import cn.jeesmart.common.model.DataEntity;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 管理员
 * 
 * @author Joe
 */
public class User extends DataEntity {

	private static final long serialVersionUID = 1106412532325860697L;
	
	/** 登录名 */
	private String account;
	/** 密码 */
	private String password;
	/** 最后登录IP */
	private String lastLoginIp;
	/** 登录总次数 */
	private Integer loginCount = Integer.valueOf(0);
	/** 最后登录时间 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
}
