package io.javabrains.moviecatalogservice.models;

public class CatalogItem {

	private String name;
	private String desc;
	private int rating;
	private String imbd_Id;
	
	public CatalogItem(String name, String desc, int rating) {
		super();
		this.name = name;
		this.desc = desc;
		this.rating = rating;
	}
	
	
	
	public CatalogItem(String name, String desc, int rating, String imbd_Id) {
		super();
		this.name = name;
		this.desc = desc;
		this.rating = rating;
		this.imbd_Id = imbd_Id;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getImbd_Rating() {
		return imbd_Id;
	}

	public void setImbd_Rating(String imbd_Id) {
		this.imbd_Id = imbd_Id;
	}
	
	
	
	
	
	
	
}
