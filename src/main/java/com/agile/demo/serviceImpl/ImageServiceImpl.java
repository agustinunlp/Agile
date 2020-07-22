package com.agile.demo.serviceImpl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.agile.demo.criteria.ImageSearchCriteria;
import com.agile.demo.domain.ImageDescription;
import com.agile.demo.domain.ImageList;
import com.agile.demo.request.AuthRequest;
import com.agile.demo.response.AuthResponse;
import com.agile.demo.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

	private static final String AUTH_URI = "http://interview.agileengine.com/auth";

	@Autowired
	@Qualifier("imagesTemplate")
	private MongoTemplate imagesTemplate;

	private String token; 

	private static final String BASE_URI = "http://interview.agileengine.com/images/";

	@PostConstruct
	public void init() {
		renewToken();
	}
	
	@Override
	@Scheduled(cron = "0 0 0/1 1/1 * ? *")
	public void initialize() {
		imagesTemplate.dropCollection(ImageDescription.class);
		int page = 1;
		boolean finish = false;	
		while(!finish) {
			ImageList retriveImagePage = this.retriveImageFeedPage(page);
			finish = !retriveImagePage.hasMore();
			page = retriveImagePage.getPage() + 1;
			retriveImagePage.getPictures().forEach(picture -> this.retrieveImage(picture.getId()));			
		}
	}

	@Override
	public ImageList retriveImageFeedPage(int page) {
		HttpHeaders headers = getAuthorization();
		RestTemplate restTemplate = new RestTemplate();
		final String uri = "http://interview.agileengine.com/images?page="+page;
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<ImageList> response = restTemplate.exchange(uri, HttpMethod.GET, entity, ImageList.class);
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			return response.getBody();			
		}
		return null;
		//TODO: instead of returning null we should throw an exception that have to be handled in the controller.Like ImageNofFoundException
	}

	private HttpHeaders getAuthorization() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+ token);
		return headers;
	}

	@Override
	public ImageDescription retrieveImage(String imageId) {	
		ImageDescription imageDescription = imagesTemplate.findById(imageId, ImageDescription.class);
		if (imageDescription != null) {
			return imageDescription;
		}
		HttpHeaders headers = getAuthorization();
		RestTemplate restTemplate = new RestTemplate();
		final String uri = BASE_URI + imageId;
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		try {
			ResponseEntity<ImageDescription> response = restTemplate.exchange(uri, HttpMethod.GET, entity, ImageDescription.class);	
			imageDescription = response.getBody();
			imagesTemplate.insert(imageDescription);
			return imageDescription; 
		} catch (HttpClientErrorException e) {
			if(e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
				this.renewToken();
				return this.retrieveImage(imageId);	  		
			}
		}
		return null;		
		//TODO: instead of returning null we should throw an exception that have to be handled in the controller.Like ImageNofFoundException

	}

	@Override
	public List<ImageDescription> retrieveImageByCriteria(ImageSearchCriteria imageCriteria) {
		Query query = new Query(Criteria 
				.where(imageCriteria.getField()).is(imageCriteria.getValue()));
		return imagesTemplate.find(query, ImageDescription.class);
	}

	private void renewToken() {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		AuthRequest request = new AuthRequest();
		HttpEntity<?> httpEntity = new HttpEntity<Object>(request, headers);
		ResponseEntity<AuthResponse> result = restTemplate.exchange(AUTH_URI, HttpMethod.POST, httpEntity, AuthResponse.class);
		if(result.getStatusCode().equals(HttpStatus.OK)) {
			this.setToken(result.getBody().getToken());
		}
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
