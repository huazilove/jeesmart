package cn.jeesmart.sso.server.model;

import cn.jeesmart.common.model.DataEntity;

/**
 * 应用
 * 
 * @author Joe
 */
public class App extends DataEntity {

	private static final long serialVersionUID = 7902814112969375973L;
	
	/** 名称 */
	private String name;
	/** 编码  */
	private String code;
	/** 排序 */
	private Integer sort = Integer.valueOf(1);


	
	public App(){
	}
	
	public App(String name, String code, Integer sort) {
		super();
		this.name = name;
		this.code = code;
		this.sort = sort;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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
