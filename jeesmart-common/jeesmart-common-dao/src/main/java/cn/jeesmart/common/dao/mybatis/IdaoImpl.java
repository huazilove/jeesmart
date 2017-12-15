package cn.jeesmart.common.dao.mybatis;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

/**
 * DAO层封装接口，包含常用的CURD和分页操作
 * @author wjh
 * @create 2017/12/15
 * @param <T>
 * @param <PK>
 */
@Service
public class IdaoImpl<T, PK extends Serializable> extends SqlSessionDaoSupport
		implements Idao<T, Serializable> {
	@Override
	public void delete(Class<T> entityClass, Serializable pk) {
		getSqlSession().delete(entityClass.getName() + ".delete", pk);
	}
	@Override
	public void update(T entity) {
		getSqlSession().update(entity.getClass().getName() + ".update", entity);
	}
	@Override
	public T findById(Class<T> entityClass, Serializable pk) {
		return getSqlSession().selectOne(entityClass.getName() + ".findById",
				pk);
	}
	@Override
	public List<T> findAll(Class<T> entityClass) {
		return getSqlSession().selectList(entityClass.getName() + ".findAll");
	}
	@Override
	public void save(T entity) {
		getSqlSession().insert(entity.getClass().getName() + ".add", entity);
	}
	@Override
	public Pager<T> findByPage(Class<T> entityClass, int pageNo, int pageSize) {
		return findByPage(entityClass, pageNo, pageSize, null);
	}
	@Override
	public Pager<T> findByPage(Class<T> entityClass, int pageNo, int pageSize,
			Object key) {
		Pager<T> pager = new Pager<T>();
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("pageNo", pageNo);
		maps.put("pageSize", pageSize);
		if (key != null) {
			maps.put("category", key);
		}
		List<T> pageList = getSqlSession().selectList(
				entityClass.getName() + ".findByPage", maps);
		int totalNum = getTotalNum(entityClass, key);
		pager.setPageList(pageList);
		pager.setTotalNum(totalNum);
		return pager;
	}

	private int getTotalNum(Class<T> entityClass, Object key) {
		int totalNum = 0;
		if (key != null) {
			totalNum = getSqlSession().selectOne(
					entityClass.getName() + ".findTotal", key);
		} else {
			totalNum = getSqlSession().selectOne(
					entityClass.getName() + ".findTotal");
		}
		return totalNum;
	}
	@Override
	public void updateOrder(T entity) {
		getSqlSession().update(entity.getClass().getName() + ".updateOrder",
				entity);
	}
	@Override
	public T login(T entity) {
		return getSqlSession().selectOne(
				entity.getClass().getName() + ".login", entity);
	}

	/**
	 * 多条件查询 得到集合
	 * @param entityClass
	 * @param maps
	 * @param operate
	 * @return
	 */
	@Override
	public Pager<T> findByKey(Class<T> entityClass, Map<String, Object> maps,
			String operate) {
		Pager<T> pager = new Pager<T>();
		List<T> pageList = getSqlSession().selectList(
				entityClass.getName() + operate, maps);
		int totalNum = getTotalNum(entityClass, maps, operate);
		pager.setPageList(pageList);
		pager.setTotalNum(totalNum);
		return pager;
	}

	/**
	 * 多条件的查询 得到总和
	 * @param entityClass
	 * @param maps
	 * @param operate
	 * @return
	 */
	private int getTotalNum(Class<T> entityClass, Map<String, Object> maps,
			String operate) {
		int totalNum = 0;
		totalNum = getSqlSession().selectOne(
				entityClass.getName() + operate + "Total", maps);
		return totalNum;
	}
    @Override
	public int isExist(Class<T> entityClass, Map<String, Object> maps,
			String operate) {
		int count = 0;
		count = getSqlSession()
				.selectOne(entityClass.getName() + operate, maps);
		return count;
	}
	@Override
	public T findByParam(Class<T> entityClass, Map<String, Object> maps,
			String operate) {
		return getSqlSession().selectOne(entityClass.getName() + operate, maps);
	}
	@Override
	public List<T> findAllByKey(Class<T> entityClass, Map<String, Object> maps,String operate) {
		return getSqlSession().selectList(entityClass.getName() + operate,maps);
	}
}
