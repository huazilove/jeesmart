package cn.jeesmart.common.model;

/**
 * 键值对基础接口 
 * 
 * @author Joe
 */
public interface Itemable {
	/**
	 *键
	 * @return
	 */
	public String getLabel();

	/**
	 * 值
	 * @return
	 */
	public Object getValue();
}
