package com.example.RatingsDataServices.Model;

import java.util.List;


//created an object of new userRating so that it will be easy for us to make changes in the future
// instead of returning a list which is the root of an object
public class UserRating {
	private List<Rating> userRating;

	public List<Rating> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<Rating> userRating) {
		this.userRating = userRating;
	}
	
	
}
