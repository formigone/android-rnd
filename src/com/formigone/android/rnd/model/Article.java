package com.formigone.android.rnd.model;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Article {
	private int id;
	private String title;
	private String img;
	private String content;
	private String author;
	private String date;
	private String summary;
	
	public Article() {}
	public Article(String title, String img, String date, String content) {
		this.title = title;
		this.img = img;
		this.date = date;
		this.content = content;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Article(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}
}
