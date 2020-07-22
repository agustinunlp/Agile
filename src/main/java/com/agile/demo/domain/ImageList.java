package com.agile.demo.domain;

import java.util.List;

public class ImageList {
	private List<Image> pictures;
	private int page;
	private int pageCount;
	private boolean hasMore;	
	
	public ImageList() {
		super();
	}

	public ImageList(List<Image> pictures, int page, int pageCount, boolean hasMore) {
		super();
		this.pictures = pictures;
		this.page = page;
		this.pageCount = pageCount;
		this.hasMore = hasMore;
	}
	
	public List<Image> getPictures() {
		return pictures;
	}
	public void setPictures(List<Image> pictures) {
		this.pictures = pictures;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public boolean hasMore() {
		return hasMore;
	}
	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}
	
}
