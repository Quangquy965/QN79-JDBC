package com.laptrinhjavaweb.model;

public class CommentModel extends AbstractModel<CategoryModel> {
	private String content;
	private Long user_id;
	private Long news_id;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getNews_id() {
		return news_id;
	}
	public void setNews_id(Long news_id) {
		this.news_id = news_id;
	}
	
	
}
