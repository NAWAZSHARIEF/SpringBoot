package io.javabrains.moviecatalogservice.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRatings;

@Service
public class UserRatingService {

	@Autowired
	private RestTemplate restTemplate;
	
	
	@HystrixCommand(fallbackMethod="getFallbackUserRating")
	public UserRatings getUserRating(@PathVariable String userId) {
		return restTemplate.getForObject("http://ratings-data-service/ratingsData/users/"+userId,UserRatings.class);
	} 

	public UserRatings getFallbackUserRating(@PathVariable String userId) {
		List<Rating> ratings =  Arrays.asList(
		    	new Rating("100",4),
			    new Rating("200",5));
		
		UserRatings userRatings = new UserRatings();
		userRatings.setRatings(ratings);
		
		
		return userRatings;
	}
	
	
}
