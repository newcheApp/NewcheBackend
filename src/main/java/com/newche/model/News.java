package com.newche.model;

import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class News {
	@Id
	private String id;
	private String title;
	private String url;
	private String summary;
	private Date publishDate;
	@DBRef
    private List<Tag> tags;
	
	
	
	public News() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public News(String title, String url, String summary, Date publishDate, List<Tag> tags) {
		super();
		this.title = title;
		this.url = url;
		this.summary = summary;
		this.publishDate = publishDate;
		this.tags = tags;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
	
	public List<Tag> getTagIds() {
		return tags;
	}
	public void setTagIds(List<Tag> tags) {
		this.tags = tags;
	}

	

	public Date getPublishingDate() {
		return publishDate;
	}
	public void setPublishingDate(Date publishingDate) {
		this.publishDate = publishingDate;
	}


	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", url=" + url + ", summary=" + summary + ", publishDate="
				+ publishDate + ", tags=" + tags + "]";
	}
	
}
