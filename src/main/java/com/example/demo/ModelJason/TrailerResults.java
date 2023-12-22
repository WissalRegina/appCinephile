package com.example.demo.ModelJason;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrailerResults {
	
	@JsonProperty("id")
    private Long movie_id;

    @JsonProperty("results")
    private List<Trailer> trailers;

	public Long getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(Long movie_id) {
		this.movie_id = movie_id;
	}

	public List<Trailer> getTrailers() {
		return trailers;
	}

	public void setTrailers(List<Trailer> trailers) {
		this.trailers = trailers;
	}
    
    

}
