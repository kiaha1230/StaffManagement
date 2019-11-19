package com.team3.model;

public class Pager {
	private Integer page;
	private Integer pageSize;
	private Integer totalRow;
	private Integer firstPage;
	private Integer lastPage;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
	}
	public Integer getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(Integer firstPage) {
		this.firstPage = firstPage;
	}
	public Integer getLastPage() {
		return lastPage;
	}
	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}
	public Pager(Integer page, Integer pageSize, Integer totalRow, Integer firstPage, Integer lastPage) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.totalRow = totalRow;
		this.firstPage = firstPage;
		this.lastPage = lastPage;
	}
	public Pager() {
		super();
	}
	
	

	
}
