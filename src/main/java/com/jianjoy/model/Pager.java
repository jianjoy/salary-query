package com.jianjoy.model;

public class Pager {

	private int totalRows;

	private int pageSize = 100;

	private int currentPage = 1;

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		int remain = totalRows % pageSize;
		int pageNum = totalRows / pageSize;
		return remain == 0 ? pageNum : pageNum + 1;
	}

	public int getOffSet() {
		return (currentPage - 1) * pageSize;
	}

	@Override
	public String toString() {
		return String.format("[currentPage:%d,offset:%d,pageSize:%d,totalRows:%d,totalPage:%d]", currentPage, getOffSet(), pageSize, totalRows, getTotalPage());
	}

	public static void main(String[] args) {
		Pager p = new Pager();
		p.setPageSize(10);
		p.setTotalRows(99);
		p.setCurrentPage(2);
		System.out.println(p);

	}

}
