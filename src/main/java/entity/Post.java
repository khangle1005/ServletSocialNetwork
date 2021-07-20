package entity;

public class Post {
	private int post_id;
	private int user_id;
	private String date;
	private int likes;
	private String text;
	private String images;
	
	public Post(int post_id, int user_id, String date, int likes, String text) {
		this.post_id = post_id;
		this.user_id = user_id;
		this.date = date;
		this.likes = likes;
		this.text = text;
	}
	
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	
}
