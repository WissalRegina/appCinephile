package com.example.demo.ModelJason;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieCredits {
	
	private Long movie_id;
	
	private List<Acthor> cast;

	public Long getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(Long movie_id) {
		this.movie_id = movie_id;
	}

	public List<Acthor> getCast() {
		return cast;
	}

	public void setCast(List<Acthor> cast) {
		this.cast = cast;
	}
	
	

}
