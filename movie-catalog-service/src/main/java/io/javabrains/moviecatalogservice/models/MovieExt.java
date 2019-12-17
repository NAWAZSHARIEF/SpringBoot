package io.javabrains.moviecatalogservice.models;

public class MovieExt {

	String id;
	String imdb_id;
	String title;
	String tagline;
	String status;
	
	public MovieExt() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public MovieExt(String id, String imdb_id, String title, String tagline, String status) {
		super();
		this.id = id;
		this.imdb_id = imdb_id;
		this.title = title;
		this.tagline = tagline;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImdb_id() {
		return imdb_id;
	}
	public void setImdb_id(String imdb_id) {
		this.imdb_id = imdb_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
