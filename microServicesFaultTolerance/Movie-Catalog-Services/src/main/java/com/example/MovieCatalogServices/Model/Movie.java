package com.example.MovieCatalogServices.Model;

public class Movie {
	
	
	private String movieId;
	private String name;
	public String getMovieId() {
		return movieId;
	}
	
	public Movie() {
		
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Movie(String movieId, String name) {
		super();
		this.movieId = movieId;
		this.name = name;
	}
	
	
	
}
