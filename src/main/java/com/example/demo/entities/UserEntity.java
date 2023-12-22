package com.example.demo.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	private String fullName;
	private String userName;
	private String email;
	private String password;
	private String role;
	
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Wishes> wishes;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Favorite> favorites;
	
	 @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	 private Set<ReviewEntity> reviews;
	 
	 @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	 private Set<WatchedList> watched;
	
	
	//getter && setter
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
