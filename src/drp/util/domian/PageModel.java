package drp.util.domian;

import java.util.List;

/**
 * 页面模型类
 */
public class PageModel<E> {
    //结果集
    private List<E> list;
    //总记录数
    private int totalRecords;
    //每页多少条数据
    private int pageSize;
    //第几页
    private int pageNo;

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
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

    public int getTotalPageNum() {
        return (totalRecords + pageSize - 1)/pageSize;
    }

    public int getTopPageNo(){
        return 1;
    }
    public int getBottomPageNo(){
        return getTotalPageNum();
    }
    public int getPreviousPageNo(){
        return pageNo==getTopPageNo()?getTopPageNo():pageNo-1;
    }
    public int getNextPageNo(){
        return pageNo==getBottomPageNo()?getBottomPageNo():pageNo+1;
    }
    @Override
    public String toString() {
        return "PageModel{" +
                "list=" + list +
                ", totalRecords=" + totalRecords +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                '}';
    }
}
