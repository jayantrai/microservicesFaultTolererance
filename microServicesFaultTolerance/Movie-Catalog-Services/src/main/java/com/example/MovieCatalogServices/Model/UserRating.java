package com.example.MovieCatalogServices.Model;

import java.util.List;


//created an object of new userRating so that it will be easy for us to make changes in the future
// instead of returning a list which is the root of an object
public class UserRating {
	private String userId;
    private List<Rating> ratings;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

	
}
