package cn.jeesmart.common.dao.mybatis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import cn.jeesmart.common.exception.SystemException;
import cn.jeesmart.common.utils.GenericsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DAO层封装接口，包含常用的CURD和分页操作
 * @author wjh
 * @create 2017/12/15
 * @param <T>
 * @param <PK>
 */
public abstract class AbstractBaseDao<T, PK extends Serializable> implements
		BaseDao<T, PK> {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBaseDao.class);
	Class entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());
	@Resource
	private Idao<T, Serializable> idao;

	@Override
	public void save(T entity) {
		idao.save(entity);
	}

	@Override
	public void batchSave(List<T> dataList) {
		verifyRows(idao.batchSave(entityClass,dataList), dataList.size(), "数据库批量新增失败");
	}

	@Override
	public void delete(PK pk) {
		idao.delete(entityClass, pk);
	}

	@Override
	public void update(T entity) {
		idao.update(entity);
	}
	@Override
	public void batchUpdate(List<T> dataList) {
		verifyRows(idao.batchUpdate(entityClass,dataList), dataList.size(), "数据库批量修改失败");
	}

	@Override
	public T findById(PK pk) {
		return (T)idao.findById(entityClass, pk);
	}

	@Override
	public List<T> findAll() {
		return idao.findAll(entityClass);
	}

	@Override
	public Pager<T> findByPage(int pageNo,int pageSize) {
		return idao.findByPage(entityClass, pageNo, pageSize);
	}

	@Override
	public void updateOrder(T entity) {
		idao.updateOrder(entity);
	}

	@Override
	public T login(Map<String, Object> maps) {
		return (T)idao.login(entityClass,maps);
	}

	@Override
	public Pager<T> findByPage(int pageNo,int pageSize,Object key) {
		return idao.findByPage(entityClass, pageNo, pageSize, key);
	}

	@Override
	public Pager<T> findByKey(Map<String, Object> maps,String operate) {
		return idao.findByKey(entityClass, maps, operate);
	}
	@Override
	public int isExist(Map<String, Object> maps,String operate) {
		return idao.isExist(entityClass, maps, operate);
	}

	@Override
	public T findByParam(Map<String, Object> maps,String operate) {
		return (T)idao.findByParam(entityClass, maps, operate);
	}

	@Override
	public List<T> findAllByKey(Map<String, Object> maps,String operate) {
		return idao.findAllByKey(entityClass, maps, operate);
	}
	@Override
	public List<?> find(Map<String, Object> maps,String operate) {
		return idao.find(entityClass, maps, operate);
	}
	/**
	 * 为高并发环境出现的更新和删除操作，验证更新数据库记录条数
	 *
	 * @param updateRows
	 * @param rows
	 * @param message
	 */
	protected void verifyRows(int updateRows, int rows, String message) {
		if (updateRows != rows) {
			SystemException e = new SystemException(message);
			LOGGER.error("need update is {}, but real update rows is {}.", rows, updateRows, e);
			throw e;
		}
	}
}
