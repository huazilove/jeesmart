package cn.jeesmart.common.dao.hibernate;

import java.util.List;

/**
 * 查询结果集，包括数据和总数
 *
 * @author wjh
 * @create 2017/12/15
 */
public class QueryResult<T> {
    /**
     * 查询得出的数据List
     **/
    private List<T> resultList;
    /**
     * 查询得出的总数
     **/
    private int totalRecord;

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
}
