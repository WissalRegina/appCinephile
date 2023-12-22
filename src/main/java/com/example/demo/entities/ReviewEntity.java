package com.example.demo.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class ReviewEntity {
	@EmbeddedId
	private ReviewCle id_review;
	private int rating;
	private String comment;
	private String avatar;
	
	@ManyToOne(fetch = FetchType.EAGER)
    private UserEntity user;
    @ManyToOne(fetch = FetchType.EAGER)
    private MovieEntity movie;
	public ReviewCle getId_review() {
		return id_review;
	}
	public void setId_review(ReviewCle id_review) {
		this.id_review = id_review;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
	public ReviewEntity() {
		super();
	}
    
   
	
	
	

}
