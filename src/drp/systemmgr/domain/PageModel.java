package drp.systemmgr.domain;

import java.util.List;

/**
 * 页面模型类
 */
public class PageModel {
    //结果集
    private List list;
    //总记录数
    private int totalRecords;
    //每页多少条数据
    private int pageSize;
    //第几页
    private int pageNo;
    //总页数
    private int totalPageSize;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalPageSize() {
        return totalPageSize;
    }

    public void setTotalPageSize(int totalPageSize) {
        this.totalPageSize = totalPageSize;
    }

    @Override
    public String toString() {
        return "PageModel{" +
                "list=" + list +
                ", totalRecords=" + totalRecords +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", totalPageSize=" + totalPageSize +
                '}';
    }
}
