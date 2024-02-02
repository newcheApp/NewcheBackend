package com.newche.model;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	
	private static final Logger logger = LoggerFactory.getLogger(News.class);
	
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
		logger.debug("News object created: {}", this.toString());
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
		logger.debug("ID set to: {}", id);
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
		logger.debug("Title set to: {}", title);
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
		logger.debug("URL set to: {}", url);
	}
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
		logger.debug("Summary set to: {}", summary);
	}
	
	public List<Tag> getTagIds() {
		return tags;
	}
	public void setTagIds(List<Tag> tags) {
		this.tags = tags;
		logger.debug("Tags set to: {}", tags);

	}

	public Date getPublishingDate() {
		return publishDate;
	}
	public void setPublishingDate(Date publishingDate) {
		this.publishDate = publishingDate;
		logger.debug("Publishing Date set to: {}", publishingDate);

	}


	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", url=" + url + ", summary=" + summary + ", publishDate="
				+ publishDate + ", tags=" + tags + "]";
	}
	
}
