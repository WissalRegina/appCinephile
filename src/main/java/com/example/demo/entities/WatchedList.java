package com.example.demo.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class WatchedList {
	@EmbeddedId
	private WatchedCle id;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    private UserEntity user;
    @ManyToOne(fetch = FetchType.EAGER)
    private MovieEntity movie;
	public WatchedList() {
		super();
	}
	public WatchedList(WatchedCle id, UserEntity user, MovieEntity movie) {
		super();
		this.id = id;
		this.user = user;
		this.movie = movie;
	}
	public WatchedCle getId() {
		return id;
	}
	public void setId(WatchedCle id) {
		this.id = id;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public MovieEntity getMovie() {
		return movie;
	}
	public void setMovie(MovieEntity movie) {
		this.movie = movie;
	}
    
    

}
