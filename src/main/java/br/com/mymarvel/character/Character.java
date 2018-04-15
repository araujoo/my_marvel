package br.com.mymarvel.character;

public class Character {

	private String thumb_url;
	private int id;
	
	public Character() {}
	
	public Character(String thumb_url, int id) {
		super();
		this.thumb_url = thumb_url;
		this.id = id;
	}	
	
	
	public String getthumb_url() {
		return thumb_url;
	}
	public void setthumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}	
