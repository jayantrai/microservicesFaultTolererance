package com.example.MovieCatalogServices.Resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.MovieCatalogServices.Model.CatalogItem;
import com.example.MovieCatalogServices.Model.Movie;
import com.example.MovieCatalogServices.Model.Rating;
import com.example.MovieCatalogServices.Model.UserRating;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	
	@RequestMapping("/{userId}")
	@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
//		RestTemplate restTemplate = new RestTemplate();
		
//		another method of creating mircoservice calls is webClient.Builder()
		
		// hardcoding a list of ratings using arrays
//		List<Rating> ratings = Arrays.asList(
//				 new Rating("1234", 4),
//				 new Rating("5678", 3)
//				);
		
//		IMPORTANT
		
		// instead of hardcord list we are going to use a rest template 
		UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);
		
		
		// looping through arrays
		// making a call to external api which combines ratings and movie info
		return userRating.getRatings().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
			
			
//			Movie movie = webClientBuilder.build()
//			.get() // .post for post
//			.uri("http://localhost:8082/movies/" + rating.getMovieId())
//			.retrieve()
//			.bodyToMono(Movie.class)
//			.block();
			
			return new CatalogItem(movie.getName(), "Making an api call", rating.getRating());
			
		})

		.collect(Collectors.toList());
	}
		
//		return Collections.singletonList(
//				new CatalogItem("Titanic", "It is a good movie", 4)
//				);
		public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
			return Arrays.asList(new CatalogItem("No Movie", "", 0));
		}
		

}
