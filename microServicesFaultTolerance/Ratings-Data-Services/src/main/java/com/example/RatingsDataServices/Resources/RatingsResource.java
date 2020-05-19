package com.example.RatingsDataServices.Resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RatingsDataServices.Model.Rating;
import com.example.RatingsDataServices.Model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {
	
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}
	
	
	@RequestMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
				List<Rating> ratings = Arrays.asList(
				new Rating("1234", 4),
				new Rating("5678", 4)
				);
				
		// created an object of new userRating so that it will be easy for us to make changes in the future
		// instead of returning a list, this will also be used to make a call to the api
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratings);
		return userRating;
		
	}
	
}
