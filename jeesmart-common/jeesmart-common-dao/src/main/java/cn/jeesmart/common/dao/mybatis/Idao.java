package cn.jeesmart.common.dao.mybatis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * DAO层封装接口，包含常用的CURD和分页操作
 * @author wjh
 * @create 2017/12/15
 * @param <T>
 * @param <PK>
 */
 interface Idao<T,PK extends Serializable> {
	/**
	 * 增加
	 * @param entity
	 */
    void save(T entity);
   /**
    * 删除
    * @param pk
    */
    void delete(Class<T> entityClass, PK pk);
   /**
    * 修改
    * @param entity
    */
    void update(T entity);
   /**
    * 查询 按ID
    * @param pk
    */
    T findById(Class<T> entityClass, PK pk);
   /**
    * 查询全部信息
    * @return
    */
    List<T> findAll(Class<T> entityClass);
   /**
    * 分页信息
    * @param entityClass
    * @param pageNo
    * @param pageSize
    * @return
    */
    Pager<T> findByPage(Class<T> entityClass, int pageNo, int pageSize);
   /**
    * 排序
    * @param entity
    */
    void updateOrder(T entity);
   /**
    * 登陆
    * @param maps
    * @param entityClass
    * @return
    */
    T login(Class<T> entityClass, Map<String, Object> maps);
   /**
    * 带一个条件的分页
    * @param entityClass
    * @param pageNo
    * @param pageSize
    * @param key
    * @return
    */
    Pager<T> findByPage(Class<T> entityClass, int pageNo, int pageSize, Object key);
   /**
    * 带多条件的分页
    * @param entityClass
    * @param maps
    * @param operate
    * @return
    */
    Pager<T> findByKey(Class<T> entityClass, Map<String, Object> maps, String operate);
   /**
    * 判断某个值是否存在
    * @param entityClass
    * @param maps
    * @param operate
    * @return
    */
    int isExist(Class<T> entityClass, Map<String, Object> maps, String operate);
   /**
    * 通过一个参数取到对应的对象
    * @param entityClass
    * @param maps
    * @param operate
    * @return
    */
    T findByParam(Class<T> entityClass, Map<String, Object> maps, String operate);
   /**
    * 根据条件 查询所有内容
    * @param entityClass
    * @param maps
    * @param operate
    * @return
    */
    List<T> findAllByKey(Class<T> entityClass, Map<String, Object> maps, String operate);

    /**
     * 批量保存
     * @param entityClass
     * @param dataList
     * @return
     */
     int batchSave(Class<T> entityClass,List<T> dataList);
    /**
     * 批量修改
     * @param entityClass
     * @param dataList
     * @return
     */
     int batchUpdate(Class<T> entityClass,List<T> dataList);
    /**
     * 根据条件 查询所有内容
     * @param entityClass
     * @param maps
     * @param operate
     * @return
     */
    List<?> find(Class<T> entityClass, Map<String, Object> maps, String operate);
}
