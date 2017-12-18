package cn.jeesmart.common.dao.hibernate;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * DAO层封装接口，包含常用的CURD和分页操作
 * @author wjh
 * @create 2017/12/15
 */
public interface IBaseDao<T>
{

	/**
	 * 根据属性查找对象
	 *
	 * @param propertyName
	 *            属性（对应Bean）
	 * @param value
	 *            属性
	 * @return 根据属性查找对象
	 */
	 List<?> findByProperty(String propertyName, Object value);

	/**
	 * 根据实体查找对象
	 *
	 * @param entiey
	 *            实体（T类型）
	 * @return 根据属性查找对象
	 */
	 List<?> findByEntity(Object entiey);

	/**
	 * 获取记录总数
	 * @return
	 */
	 int getCount();

	/**
	 * 保存实体
	 *
	 * @param entity
	 *            实体id
	 */
	 void save(Object entity);

	/**
	 * 更新实体
	 *
	 * @param entity
	 *            实体id
	 */
	 void update(Object entity);

	/**
	 * 新增或修改
	 * @param entity
	 */
	 void saveOrUpdate(Object entity);

	/**
	 * 删除实体
	 * @param entityids
	 *            实体id数组
	 */
	 void delete(Serializable... entityids);

	/**
	 * 获取实体
	 *
	 * @param entityId
	 *            实体id
	 * @return
	 */
	 T find(Serializable entityId);

	/**
	 * 获取分页数据
	 * @param firstindex 开始索引
	 * @param maxresult 每页显示记录数
	 * @param wherejpql where语句
	 * @param queryParams 查询参数
	 * @param orderby 排序序列
	 * @return 分页数据
	 */
	 QueryResult<T> getScrollData(final int firstindex, final int maxresult, final String wherejpql, final Object[] queryParams,
                                        final LinkedHashMap<String, String> orderby);

	/**
	 * 获取分页数据
	 * @param firstindex 开始索引
	 * @param maxresult 每页显示记录数
	 * @param wherejpql where语句
	 * @param queryParams 查询参数
	 * @return 分页数据
	 */
	 QueryResult<T> getScrollData(final int firstindex, final int maxresult, final String wherejpql, final Object[] queryParams);

	/**
	 * 获取分页数据
	 * @param firstindex 开始索引
	 * @param maxresult 每页显示记录数
	 * @param orderby 排序序列
	 * @return 分页数据
	 */
	 QueryResult<T> getScrollData(final int firstindex, final int maxresult, final LinkedHashMap<String, String> orderby);

	/**
	 * 获取分页数据
	 * @param firstindex 开始索引
	 * @param maxresult 每页显示记录数
	 * @return 分页数据
	 */
	 QueryResult<T> getScrollData(final int firstindex, final int maxresult);

	/**
	 * 获取所有对象
	 *
	 * @return 所有对象
	 */
	 QueryResult<T> getScrollData();
}
