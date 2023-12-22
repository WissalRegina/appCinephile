package com.example.demo.ModelJason;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewResponse {
	
	
	@JsonProperty("id")
	private int id;
    @JsonProperty("results")
    private List<Review> results;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Review> getResults() {
		return results;
	}
	public void setResults(List<Review> results) {
		this.results = results;
	}
    
    

}
