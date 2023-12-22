package com.example.demo.ModelJason;



import com.fasterxml.jackson.annotation.JsonProperty;

public class Review {
	
	@JsonProperty("id")
	private String id;

    @JsonProperty("author")
    private String author;

    @JsonProperty("content")
    private String content;
    
    @JsonProperty("url")
    private String url;
    
    @JsonProperty("author_details")
    private Avatar authordetails;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Avatar getAuthordetails() {
		return authordetails;
	}

	public void setAuthordetails(Avatar authordetails) {
		this.authordetails = authordetails;
	}
    
    

}
