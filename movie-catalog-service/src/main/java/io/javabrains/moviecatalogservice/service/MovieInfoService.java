package io.javabrains.moviecatalogservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.javabrains.moviecatalogservice.models.MovieExt;
import io.javabrains.moviecatalogservice.models.Rating;

@Service
public class MovieInfoService {

	@Autowired
	private RestTemplate restTemplate;
	
	
	@HystrixCommand(fallbackMethod="getFallbackMovieInfo")
	public MovieExt getMovieInfo(Rating rating) {
		return restTemplate.getForObject("http://movie-info-service/movies/ext/"+rating.getMovieId(),MovieExt.class);
	}
	
	
	public MovieExt getFallbackMovieInfo(Rating rating) {
		return new MovieExt("000","imbd_id000","title000","tagline000","fallback");
				
	}

	
	
}
