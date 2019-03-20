package cn.itcast.core.entity;

import java.io.Serializable;
import java.util.List;

//序列化接口Serializable
public class PageResult implements Serializable{
    //总记录数
    private long total;
    //当前页结果集
    private List rows;

    public PageResult(long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
