package com.team3.model;

public class Pager {
	private Integer pageSize;
	private Integer pageIndex;
	private Object object;
	private Integer length;
	public Pager(Integer pageSize, Integer pageIndex, Object object, Integer length) {
		super();
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
		this.object = object;
		this.length = length;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Pager() {
		super();
	}
	
	
}
