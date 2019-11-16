package com.team3.customModel;

public class Result<T> {
	private T t;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Result(String message) {
		super();
		this.message = message;
	}

	public Result() {
		super();
	}

}
