package com.bigknow.frame.vo;

import java.util.List;

/**
 * Created by Administrator on 2015/2/25.
 */
public class Page<T> implements IVo{

    private int pageNum;//第几页

    private int pageSize;//每页多少条记录

    private long total;//总条数

    private long startRow;//开始行数

    private long endRow;//结束行数

    private long pages;//一共有多少页

    private String orderBy;

    private String fuzzyField;

    private List<T> entitys;

    public List<T> getEntitys() {
        return entitys;
    }

    public void setEntitys(List<T> entitys) {
        this.entitys = entitys;
        com.github.pagehelper.Page page = ((com.github.pagehelper.Page)entitys);
        this.setPageNum(page.getPageNum());
        this.setPages(page.getPages());
        this.setStartRow(page.getStartRow());
        this.setEndRow(page.getEndRow());
        this.setTotal(page.getTotal());
    }

    public String getFuzzyField() {
        return fuzzyField;
    }

    public void setFuzzyField(String fuzzyField) {
        this.fuzzyField = fuzzyField;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getStartRow() {
        return startRow;
    }

    public void setStartRow(long startRow) {
        this.startRow = startRow;
    }

    public long getEndRow() {
        return endRow;
    }

    public void setEndRow(long endRow) {
        this.endRow = endRow;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
