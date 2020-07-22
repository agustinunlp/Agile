package com.agile.demo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.agile.demo.criteria.ImageSearchCriteria;
import com.agile.demo.domain.ImageDescription;
import com.agile.demo.service.ImageService;

@RestController
public class ImageController 
{
	@Autowired
	private ImageService imageService;
	private Logger logger = LogManager.getLogger(ImageController.class);

	/**
	 * Retrieve an Image by id
	 */
	//TODO: we should validate if the input is correct
	@GetMapping(path="/images/{id}")
	public ResponseEntity<ImageDescription> fill(@PathVariable(value = "id") String id) 
	{
		logger.info(String.format("Retrieve Image by Id: %s", id));
		ImageDescription retrieveImage = imageService.retrieveImage(id);
		if(retrieveImage != null) {
			return new ResponseEntity<ImageDescription>(retrieveImage, HttpStatus.OK);
		}
		return new ResponseEntity<ImageDescription>(retrieveImage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Retrieve Images by search term
	 */
	//TODO: explain the endpoint purpouse and the input parameters
	//TODO: we should validate if the input is correct
	@GetMapping(path="/search/{searchTerm}")
	public ResponseEntity<List<ImageDescription>> search(@PathVariable(value = "searchTerm") String searchTerm) 
	{
		logger.info(String.format("Retrieve Image list searched by criteria : %s", searchTerm));
		String[] fieldValue = searchTerm.split("=");
		ImageSearchCriteria criteria = new ImageSearchCriteria(fieldValue[0], fieldValue[1]);
		List<ImageDescription> retrieveImageByCriteria = imageService.retrieveImageByCriteria(criteria);

		if(retrieveImageByCriteria != null) {
			return new ResponseEntity<List<ImageDescription>>(retrieveImageByCriteria, HttpStatus.OK);
		}
		return new ResponseEntity<List<ImageDescription>>(retrieveImageByCriteria, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}