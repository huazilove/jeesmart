package cn.jeesmart.sso.server.model;
import cn.jeesmart.common.model.DataEntity;


/**
 * 角色权限映射
 * 
 * @author Joe
 */
public class RolePermission extends DataEntity {

	private static final long serialVersionUID = 2817362249993235590L;

	/** 应用ID */
	private String appId;
	private String roleId;
	private String permissionId;
	
	public RolePermission() {
		super();
	}
	
	public RolePermission(String appId, String roleId, String permissionId) {
		super();
		this.appId = appId;
		this.roleId = roleId;
		this.permissionId = permissionId;
	}

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
}
