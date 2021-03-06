package cn.jeesmart.common.dao.mybatis;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
/**
 * DAO层封装接口，包含常用的CURD和分页操作
 * @author wjh
 * @create 2017/12/15
 * @param <T>
 * @param <PK>
 */
public interface BaseDao<T,PK extends Serializable> {
	/**
	 * 增加
	 * @param entity
	 */
    void save(T entity);
   /**
    * 删除
    * @param pk
    */
    void delete(PK pk);
    /**
     * 删除
     * @param operate
     * @return
     */
    void delete(Map<String, Object> maps, String operate);
   /**
    * 修改
    * @param entity
    */
    void update(T entity);
   /**
    * 查询 按ID
    * @param pk
    */
    T findById(PK pk);
   /**
    * 查询全部信息
    * @return
    */
    List<T> findAll();
   /**
    * 分页
    * @param pageNo
    * @param pageSize
    * @return
    */
    Pager<T> findByPage(int pageNo, int pageSize);
   /**
    * 排序
    * @param entity
    */
    void updateOrder(T entity);
   /**
    * 登陆
    * @param maps
    * @return
    */
    T login(Map<String, Object> maps);
   /**
    * 一个条件的分页
    * @param pageNo
    * @param pageSize
    * @param key
    * @return
    */
    Pager<T> findByPage(int pageNo, int pageSize, Object key);
   /**
    * 多条件的分页查询
    * @param maps
    * @param operate
    * @return
    */
    Pager<T> findByKey(Map<String, Object> maps, String operate);
   /**
    * 判断某个值是否存在
    * @param maps
    * @param operate
    * @return
    */
    int isExist(Map<String, Object> maps, String operate);
   /**
    * 通过一个参数取到对应的对象
    * @param maps
    * @param operate
    * @return
    */
    T findByParam(Map<String, Object> maps, String operate);
   /**
    * 通过条件查询全部内容
    * @param maps
    * @param operate
    * @return
    */
    List<T> findAllByKey(Map<String, Object> maps, String operate);

    /**
     * 批量保存
     * @param dataList
     * @return
     */
     void batchSave(List<T> dataList);
    /**
     * 批量保存
     * @param dataList
     * @return
     */
     void batchUpdate(List<T> dataList);
    /**
     * 批量修改
     * @param operate
     * @return
     */
    void batchUpdate(Map<String, Object> maps,List<?> dataList, String operate);

    /**
     * 批量删除
     * @param dataList
     * @return
     */
    void batchDelete(List<?> dataList);
    /**
     * 通过条件查询全部内容
     * @param maps
     * @param operate
     * @return
     */
    List<?> find(Map<String, Object> maps, String operate);
}
