package com.example.demo.ModelJason;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Avatar {
	
	@JsonProperty("avatar_path")
	private String avatarPath;

    @JsonProperty("rating")
    private String rating;

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
    
    


}
