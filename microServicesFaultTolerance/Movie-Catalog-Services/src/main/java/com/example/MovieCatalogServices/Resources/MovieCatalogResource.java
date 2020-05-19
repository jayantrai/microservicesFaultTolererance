package com.example.MovieCatalogServices.Resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.MovieCatalogServices.Model.CatalogItem;
import com.example.MovieCatalogServices.Model.Movie;
import com.example.MovieCatalogServices.Model.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
//		RestTemplate restTemplate = new RestTemplate();
		
//		another method of creating mircoservice calls is webClient.Builder()
		
		
		
		
		// hardcoding a list of ratings using arrays
		List<Rating> ratings = Arrays.asList(
				 new Rating("1234", 4),
				 new Rating("5678", 3)
				);
		
		// looping through arrays
		// making a call to external api
		return ratings.stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
			
			
//			Movie movie = webClientBuilder.build()
//			.get() // .post for post
//			.uri("http://localhost:8082/movies/" + rating.getMovieId())
//			.retrieve()
//			.bodyToMono(Movie.class)
//			.block();
			
			return new CatalogItem(movie.getName(), "Making an api call", rating.getRating());
			
		})

		.collect(Collectors.toList());
		
//		return Collections.singletonList(
//				new CatalogItem("Titanic", "It is a good movie", 4)
//				);
		
		
	}
}
