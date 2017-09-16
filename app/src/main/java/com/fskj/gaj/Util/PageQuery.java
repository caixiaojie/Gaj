package com.fskj.gaj.Util;


public class PageQuery {
	public  int page=1;
	public int pagesize=20;
	
	public boolean isFirstPage(){
		return page==1;
	}
	
	public void resetPage(){
		page=1;
	}
	
	public void nextPage(){
		page++;
	}


	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pagesize;
	}

	public void setPageSize(int pageSize) {
		this.pagesize = pageSize;
	}


    @Override
    public String toString() {
        return String.format("page=%d&pagesize=%d",page,pagesize);
    }
}
