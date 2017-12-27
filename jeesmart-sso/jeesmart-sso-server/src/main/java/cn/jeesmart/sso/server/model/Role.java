package cn.jeesmart.sso.server.model;
import cn.jeesmart.common.model.DataEntity;
/**
 * 角色
 * 
 * @author Joe
 */
public class Role extends DataEntity {

	private static final long serialVersionUID = 564115576254799088L;

	/** 应用ID */
	private String appId;
	/** 名称 */
	private String name;
	/** 排序 */
	private Integer sort = Integer.valueOf(1);
	/** 描述 */
	private String description;

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/** 以下为显示辅助参数 */
	private Boolean isChecked = Boolean.valueOf(false);
	
	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}
}
