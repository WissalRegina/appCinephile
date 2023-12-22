package com.example.demo.ModelJason;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieResponse {
	
	@JsonProperty("page")
    Integer page;

    @JsonProperty("total_results")
    Long total_results;

    @JsonProperty("total_pages")
    Long total_pages;
    
    @JsonProperty("results")
    List<Movie> results;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Long getTotal_results() {
		return total_results;
	}

	public void setTotal_results(Long total_results) {
		this.total_results = total_results;
	}

	public Long getTotal_pages() {
		return total_pages;
	}

	public void setTotal_pages(Long total_pages) {
		this.total_pages = total_pages;
	}

	public List<Movie> getResults() {
		return results;
	}

	public void setResults(List<Movie> results) {
		this.results = results;
	}
    
    

}
