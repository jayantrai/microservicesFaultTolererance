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
import com.example.MovieCatalogServices.Services.MovieInfo;
import com.example.MovieCatalogServices.Services.UserRatingInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserRatingInfo userRatingInfo;
	
	
	@RequestMapping("/{userId}")
//	@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		

		UserRating userRating = userRatingInfo.getUserRating(userId);
		
		return userRating.getRatings().stream().map(rating -> movieInfo.getCatalogItem(rating))

		.collect(Collectors.toList());
	}


//	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
//	private CatalogItem getCatalogItem(Rating rating) {
//		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
//
//		return new CatalogItem(movie.getName(), "Making an api call", rating.getRating());
//	}
//
//
//	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
//	private UserRating getUserRating(String userId) {
//		return restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);
//	}
//		
//	private UserRating getFallbackUserRating(@PathVariable("userId") String userId) {
//		UserRating userRating = new UserRating();
//		userRating.setUserId(userId);
//		userRating.setRatings(Arrays.asList(
//				new Rating("0", 0)
//				));
//		return userRating;
//		
//	}
//	
//	
//	private CatalogItem getFallbackCatalogItem(Rating rating) {
//		return new CatalogItem("Movie not found", "", rating.getRating());
//	}
//	

}

//Movie movie = webClientBuilder.build()
//.get() // .post for post
//.uri("http://localhost:8082/movies/" + rating.getMovieId())
//.retrieve()
//.bodyToMono(Movie.class)
//.block();
