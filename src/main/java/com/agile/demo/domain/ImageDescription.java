package com.agile.demo.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "images")
public class ImageDescription extends Image {
	private String author;
	private String camera;
	private String tags;
	private String fullPicture;		
	
	public ImageDescription() {
		super();
	}
	
	public ImageDescription(String id, String croppedPicture, String author, String camera, String tags,
			String fullPicture) {
		super(id, croppedPicture);
		this.author = author;
		this.camera = camera;
		this.tags = tags;
		this.fullPicture = fullPicture;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCamera() {
		return camera;
	}
	public void setCamera(String camera) {
		this.camera = camera;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getFullPicture() {
		return fullPicture;
	}
	@JsonProperty("full_picture")
	public void setFullPicture(String fullPicture) {
		this.fullPicture = fullPicture;
	}


}
