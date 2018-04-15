package br.com.mymarvel.character;

public class Character {

	private String thumb_url;
	private int id;
	private String name;
	
	public Character() {}
	
	public Character(String thumb_url, int id, String name) {
		super();
		this.thumb_url = thumb_url;
		this.id = id;
		this.name = name;
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
