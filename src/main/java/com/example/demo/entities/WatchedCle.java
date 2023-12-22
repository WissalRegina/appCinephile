package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class WatchedCle {
	
	@Column(name="idUser")
	private long id;
	@Column(name="idMovie")
	private long movieId;
	public WatchedCle() {
		super();
	}
	public WatchedCle(long id, long movieId) {
		super();
		this.id = id;
		this.movieId = movieId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getMovieId() {
		return movieId;
	}
	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}
	
	

}
