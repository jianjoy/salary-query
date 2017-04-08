package com.jianjoy.dao.dbbean;

public abstract class AbstractDbDataBean<T> {
	public abstract void convertData(Object[] data);
	public abstract T getModel();
}
