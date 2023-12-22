package com.example.demo.ModelJason;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Acthor {
	
	@JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("profile_path")
    private String profile_image;

    @JsonProperty("character")
    private String character;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public String character() {
		return character;
	}

	public void character(String character) {
		this.character = character;
	}
    
    

}
