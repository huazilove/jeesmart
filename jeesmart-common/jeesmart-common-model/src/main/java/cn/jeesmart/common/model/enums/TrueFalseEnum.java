package cn.jeesmart.common.model.enums;

import cn.jeesmart.common.model.EnumItemable;

/**
 * 是否枚举
 * 
 * @author Joe
 */
public enum TrueFalseEnum implements EnumItemable<TrueFalseEnum> {
	/**
	 * 是
	 */
	TRUE("是", true),
	/**
	 * 否
	 */
	FALSE("否", false);

	private String label;
	private Boolean value;

	private TrueFalseEnum(String label, Boolean value) {
		this.label = label;
		this.value = value;
	}
    @Override
	public String getLabel() {
		return this.label;
	}
	@Override
	public Boolean getValue() {
		return this.value;
	}
}
