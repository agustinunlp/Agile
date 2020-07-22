package com.agile.demo.domain;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {
	@Id
	private String id;
	private String croppedPicture;
	
	public Image() {
		super();
	}

	public Image(String id, String croppedPicture) {
		super();
		this.id = id;
		this.croppedPicture = croppedPicture;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCroppedPicture() {
		return croppedPicture;
	}
	@JsonProperty("cropped_picture")
	public void setCroppedPicture(String croppedPicture) {
		this.croppedPicture = croppedPicture;
	}
	
}
