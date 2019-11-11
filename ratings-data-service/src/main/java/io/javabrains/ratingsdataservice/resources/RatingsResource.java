package io.javabrains.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.ratingsdataservice.models.Rating;

@RestController
@RequestMapping("/ratingsData")
public class RatingsResource {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId,4);
	}
	
	
	@RequestMapping("/{userId}")
	public List<Rating> getUserRatings(@PathVariable("userId") String userId) {
		List<Rating> ratings =  Arrays.asList(
		    	new Rating("001",4),
			    new Rating("002",5));
		return ratings;
	}
	
}
