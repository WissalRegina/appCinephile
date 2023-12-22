package com.example.demo.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class MovieEntity {
	 @Id
	 private Long movieId;
	 private String name;
	 private String description;
	 
	 @OneToMany(mappedBy = "movie")
	 private Set<Wishes> wishes; // set pour qu'il n'accepte pas les doublons
	 
	 @OneToMany(mappedBy = "movie")
	 private Set<Favorite> favorites; // set pour qu'il n'accepte pas les doublons
	 
	 @OneToMany(mappedBy = "movie")
	 private Set<ReviewEntity> reviews; // set pour qu'il n'accepte pas les doublons
	 
	 @OneToMany(mappedBy = "movie")
	 private Set<WatchedList> watched; // set pour qu'il n'accepte pas les doublons

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Wishes> getWishes() {
		return wishes;
	}

	public void setWishes(Set<Wishes> wishes) {
		this.wishes = wishes;
	}

	public Set<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(Set<Favorite> favorites) {
		this.favorites = favorites;
	}

	public Set<ReviewEntity> getReviews() {
		return reviews;
	}

	public void setReviews(Set<ReviewEntity> reviews) {
		this.reviews = reviews;
	}

	public Set<WatchedList> getWatched() {
		return watched;
	}

	public void setWatched(Set<WatchedList> watched) {
		this.watched = watched;
	}
	
	
	
	 
	 

}
