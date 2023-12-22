package com.example.demo.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class Favorite {
	
	@EmbeddedId
	private FavoriteCle id;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    private UserEntity user;
    @ManyToOne(fetch = FetchType.EAGER)
    private MovieEntity movie;
	public FavoriteCle getId() {
		return id;
	}
	public void setId(FavoriteCle id) {
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
