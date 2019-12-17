package io.javabrains.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.MovieExt;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRatings;
import io.javabrains.moviecatalogservice.service.MovieInfoService;
import io.javabrains.moviecatalogservice.service.UserRatingService;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {                                                                                                                                                                                                                                                                                                                                                                                                                                                        

	@Autowired
	private RestTemplate restTemplate;
	
	
	@Autowired
	private MovieInfoService movieInfoService;
	
	@Autowired
	private UserRatingService userRatingService;
	
	/*@Autowired
	private WebClient.Builder builder;*/
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
		
		UserRatings userRating = userRatingService.getUserRating(userId);
		List<Rating> ratings = userRating.getRatings();
	    List<CatalogItem> catlogs =			     
			     ratings.stream().map(rating -> {
			     MovieExt movie =  movieInfoService.getMovieInfo(rating);
			     return new CatalogItem(movie.getTitle(), movie.getTagline(), rating.getRating(),movie.getImdb_id());
			     })
	            .collect(Collectors.toList());
	    
	    /*List<CatalogItem> catlogs = 
	     
	     ratings.stream().map(rating -> {
	     Movie movie =  restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(),Movie.class);
	     return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
	     })
       .collect(Collectors.toList());*/

	    
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
