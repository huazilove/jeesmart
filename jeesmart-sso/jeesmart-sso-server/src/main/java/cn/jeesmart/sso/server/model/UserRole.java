package cn.jeesmart.sso.server.model;
import cn.jeesmart.common.model.DataEntity;

/**
 * 管理员角色映射
 * 
 * @author Joe
 */
public class UserRole extends DataEntity {

	private static final long serialVersionUID = 4942358338145288018L;

	/** 应用ID */
	private String appId;
	/** 管理员ID */
	private String userId;
	/** 角色ID */
	private String roleId;
	
	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
