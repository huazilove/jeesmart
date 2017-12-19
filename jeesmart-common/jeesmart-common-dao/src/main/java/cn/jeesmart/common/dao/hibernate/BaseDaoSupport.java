package cn.jeesmart.common.dao.hibernate;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import cn.jeesmart.common.utils.GenericsUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * DAO层封装使用了泛型，包含常用的CURD和分页操作
 *
 * @author wjh
 * @create 2017/12/15
 */
@SuppressWarnings("unchecked")
public abstract class BaseDaoSupport<T> extends HibernateDaoSupport implements IBaseDao<T> {
    protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(this.getClass());
    protected String entityClassName = getEntityName(this.entityClass);
    protected String keyFieldName = getKeyFieldName(this.entityClass);

    // 为父类HibernateDaoSupport注入sessionFactory的值
    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<?> findByEntity(Object entiey) {
        return super.getHibernateTemplate().findByExample(entiey);
    }

    @Override
    public List<?> findByProperty(String propertyName, Object value) {
        String queryString = "from " + entityClassName + " o where o." + propertyName + "= ?";
        return super.getHibernateTemplate().find(queryString, value);
    }

    @Override
    public void delete(Serializable... entityids) {
        for (Object id : entityids) {
            super.getHibernateTemplate().delete(find((Serializable) id));
        }
    }

    @Override
    public T find(Serializable entityId) {
        if (null != entityId) {
            return (T) super.getHibernateTemplate().get(entityClass, entityId);
        }
        return null;
    }

    @Override
    public int getCount() {
        String hql = "select count( " + keyFieldName + ") from " + entityClassName;
        int count = Integer.parseInt(super.getHibernateTemplate().find(hql).get(0).toString());
        return count;
    }

    @Override
    public void save(Object entity) {
        super.getHibernateTemplate().save(entity);
    }

    @Override
    public void update(Object entity) {
        super.getHibernateTemplate().update(entity);
    }

    @Override
    public void saveOrUpdate(Object entity) {
        super.getHibernateTemplate().saveOrUpdate(entity);
    }

    @Override
    public QueryResult<T> getScrollData(final int firstindex, final int maxresult, final String wherejpql, final Object[] queryParams,
                                        final LinkedHashMap<String, String> orderby) {
        final QueryResult<T> queryResult = new QueryResult<T>();

        super.getHibernateTemplate().execute(new HibernateCallback<T>() {
            @Override
            public T doInHibernate(Session session) throws HibernateException {
                String hql = "from " + entityClassName + " o " + (wherejpql == null || "".equals(wherejpql.trim()) ? "" : " where " + wherejpql)
                        + buildOrderby(orderby);
                Query query = session.createQuery(hql);
                setQueryParams(query, queryParams);
                queryResult.setTotalRecord(query.list().size());

                if (firstindex != -1 && maxresult != -1) {
                    query.setFirstResult(firstindex).setMaxResults(maxresult);
                }
                queryResult.setResultList(query.list());

                return null;
            }

        });

        return queryResult;

    }

    @Override
    public QueryResult<T> getScrollData(int firstindex, int maxresult, String wherejpql, Object[] queryParams) {
        return getScrollData(firstindex, maxresult, wherejpql, queryParams, null);
    }

    @Override
    public QueryResult<T> getScrollData(final int firstindex, final int maxresult, final LinkedHashMap<String, String> orderby) {
        return getScrollData(firstindex, maxresult, null, null, orderby);

    }

    @Override
    public QueryResult<T> getScrollData(final int firstindex, final int maxresult) {
        return getScrollData(firstindex, maxresult, null, null, null);
    }

    @Override
    public QueryResult<T> getScrollData() {
        return getScrollData(-1, -1, null, null, null);
    }

    /**
     * 获取实体的名称
     *
     * @param <E>
     * @param clazz 实体类
     * @return
     */
    protected static <E> String getEntityName(Class<E> clazz) {
        String entityname = clazz.getSimpleName();
        Entity entity = clazz.getAnnotation(Entity.class);
        if (entity.name() != null && !"".equals(entity.name())) {
            entityname = entity.name();
        }
        return entityname;
    }

    /**
     * 获取实体的主键
     *
     * @param <E>
     * @param clazz 实体类
     * @return 主键名
     */
    protected static <E> String getKeyFieldName(Class<E> clazz) {
        try {
            PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
            for (PropertyDescriptor propertydesc : propertyDescriptors) {
                Method method = propertydesc.getReadMethod();
                if (null != method && null != method.getAnnotation(javax.persistence.Id.class)) {
                    return propertydesc.getName();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "id";
    }

    /**
     * 设置HQL里边的属性值
     *
     * @param query
     * @param queryParams
     */
    protected static void setQueryParams(Query query, Object[] queryParams) {
        if (queryParams != null && queryParams.length > 0) {
            for (int i = 0; i < queryParams.length; i++) {
                // 从0开始
                query.setParameter(i, queryParams[i]);
            }
        }
    }

    /**
     * 组装order by语句
     *
     * @param orderby
     * @return
     */
    protected static String buildOrderby(LinkedHashMap<String, String> orderby) {
        StringBuffer orderbyql = new StringBuffer("");
        if (orderby != null && orderby.size() > 0) {
            orderbyql.append(" order by ");
            for (String key : orderby.keySet()) {
                orderbyql.append("o.").append(key).append(" ").append(orderby.get(key)).append(",");
            }
            orderbyql.deleteCharAt(orderbyql.length() - 1);
        }
        return orderbyql.toString();
    }

    protected static <E> String getCountField(Class<E> clazz) {
        String out = "o";
        try {
            PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
            for (PropertyDescriptor propertydesc : propertyDescriptors) {
                Method method = propertydesc.getReadMethod();
                if (method != null && method.isAnnotationPresent(EmbeddedId.class)) {
                    PropertyDescriptor[] ps = Introspector.getBeanInfo(propertydesc.getPropertyType()).getPropertyDescriptors();
                    out = "o." + propertydesc.getName() + "." + (!"class".equals(ps[1].getName())? ps[1].getName() : ps[0].getName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }
}
