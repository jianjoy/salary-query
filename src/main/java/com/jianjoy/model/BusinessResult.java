package com.jianjoy.model;

public class BusinessResult<T> {
	private T data;

	private String error;

	private Pager pager;

	public T getData() {
		return data;
	}

	public void setData(T t) {
		this.data = t;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

}
