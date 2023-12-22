package com.example.demo.ModelJason;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenreResponse {
	
	 @JsonProperty("genres")
	    private List<Genre> genres;

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	 
	 

}
