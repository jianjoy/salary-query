package com.jianjoy.dao;

import java.util.List;

import com.jianjoy.dao.dbbean.AbstractDbDataBean;
import com.jianjoy.model.Pager;


public interface IPageDao {
	 <T extends AbstractDbDataBean> List<T> findByPager(Class<T> clz, String sql, List<Object> params, Pager pager, Boolean desc);
}
