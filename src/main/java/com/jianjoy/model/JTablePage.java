package com.jianjoy.model;

import java.util.HashMap;
import java.util.List;
import com.alibaba.fastjson.JSON;

public class JTablePage {
	
	
	public JTablePage(List dataList,Pager p){
		this.list = dataList;
		this.pager = p;
	}

	private List list;
	
	
	private Pager pager;

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}


	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
	
	public String buildJsonString(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("Result", "OK");
		map.put("Records", list);
		pager.getTotalPage();
		map.put("TotalRecordCount", pager.getTotalRows());
		return JSON.toJSONString(map);
	}
	
}
