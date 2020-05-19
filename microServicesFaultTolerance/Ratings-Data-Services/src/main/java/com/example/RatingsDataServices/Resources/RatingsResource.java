package com.example.RatingsDataServices.Resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RatingsDataServices.Model.Rating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {
	
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}
	
	
	@RequestMapping("/users/{userId}")
	public List<Rating> getUserRating(@PathVariable("userId") String userId) {
				List<Rating> ratings = Arrays.asList(
				new Rating("1234", 3),
				new Rating("5678", 4)
				);
		return ratings;
	}
	
}
