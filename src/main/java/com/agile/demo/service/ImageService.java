package com.agile.demo.service;

import java.util.List;

import com.agile.demo.criteria.ImageSearchCriteria;
import com.agile.demo.domain.ImageDescription;
import com.agile.demo.domain.ImageList;

public interface ImageService {
	
	void initialize();
	
	ImageList retriveImageFeedPage(int page);	
	
	ImageDescription retrieveImage(String imageId);
	
	List<ImageDescription> retrieveImageByCriteria(ImageSearchCriteria imageCriteria);
}
