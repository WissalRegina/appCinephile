package com.example.demo.ModelJason;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
	
	 @JsonProperty("title")
	    private String title;
	 
	 @JsonProperty("poster_path")
	    private String posterPath;
	 
	 @JsonProperty("popularity")
	    private double popularity;
	 
	 @JsonProperty("id")
	    private Long id;
	 
	 @JsonProperty("release_date")
	    private String releaseDate;
	 
	 @JsonProperty("overview")
	    private String overview;
	 
	 @JsonProperty("genres")
	    private List<Genre> genres;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public double getPopularity() {
		return popularity;
	}

	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	
	
	 
	 

}
