package io.javabrains.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRatings;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	
	/*@Autowired
	private WebClient.Builder builder;*/
	
	@RequestMapping("/{userId}")	
	private List<CatalogItem> getCatalog(@PathVariable String userId){
		
	
		
		UserRatings userRating = restTemplate.getForObject("http://localhost:8083/ratingsData/users/"+userId,UserRatings.class);
		List<Rating> ratings = userRating.getRatings();
		
	    List<CatalogItem> catlogs = 
				     
				     ratings.stream().map(rating -> {
				     Movie movie =  restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(),Movie.class);
				     return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
				     })
		            .collect(Collectors.toList());
		
		
		/*
		 * Using Asynchronous call , Spring Reactive, WebClient
		List<CatalogItem> catlogs = 
			     
				   ratings.stream().map(rating -> {
				     Movie movie = builder.build()
				    		       .get()
				    		       .uri("http://localhost:8082/movies/"+rating.getMovieId())
				    		       .retrieve()
				    		       .bodyToMono(Movie.class)
				    		       .block();
				     return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
				     })
		            .collect(Collectors.toList());
		*/
		
		
		return catlogs;
		
		
		
	} 
	
}
