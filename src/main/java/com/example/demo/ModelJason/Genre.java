package com.example.demo.ModelJason;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Genre {
	
	 @JsonProperty("id")
	    private int id;

	 @JsonProperty("name")
	    private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	 
	 

}
