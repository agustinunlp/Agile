package com.agile.demo.criteria;

public class ImageSearchCriteria {
	private String field;
	private String value;

	public ImageSearchCriteria(String field, String value) {
		super();
		this.field = field;
		this.value = value;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
