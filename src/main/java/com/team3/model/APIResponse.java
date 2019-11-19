package com.team3.model;

public class APIResponse {
	private Pager pager;
	private Object data;

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public APIResponse(Pager pager, Object data) {
		super();
		this.pager = pager;
		this.data = data;
	}

	public APIResponse() {
		super();
	}

}
