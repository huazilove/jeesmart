package cn.jeesmart.sso.server.model;
import cn.jeesmart.common.model.DataEntity;

/**
 * 管理员应用映射
 * 
 * @author Joe
 */
public class UserApp extends DataEntity {

	private static final long serialVersionUID = 4942358338145288018L;

	/** 应用ID */
	private String appId;
	/** 管理员ID */
	private String userId;
	
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
}
