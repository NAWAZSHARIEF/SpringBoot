package io.javabrains.movieinfoservice.resources;

import java.net.InetSocketAddress;
import java.net.Proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.javabrains.movieinfoservice.models.Movie;
import io.javabrains.movieinfoservice.models.MovieExt;

@RestController
@RequestMapping("/movies")
public class MovieResource {
	
	
	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		
		return new Movie(movieId, "Test", "TestDescription");
	}
	
	
	@RequestMapping("ext/{movieId}")
	public MovieExt getMovieInfoExt(@PathVariable("movieId") String movieId) {
		
		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.55.218", 8080));
		clientHttpRequestFactory.setProxy(proxy);
		
		restTemplate = new RestTemplate(clientHttpRequestFactory);
		
		MovieExt movie=restTemplate.getForObject("http://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey, MovieExt.class);
		return movie;
	}
	
}
