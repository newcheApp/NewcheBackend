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
	private Date publishDate;
	private String body;
	private String image_url;
	private String summary;

	@DBRef
    private List<Tag> tags;
	

	private static final Logger logger = LoggerFactory.getLogger(News.class);
	
	public News() {
		// TODO Auto-generated constructor stub
	}
	
	public News(String id, String title, String url, Date publishDate, String body, String image_url, String summary,
			List<Tag> tags) {
		this.id = id;
		this.title = title;
		this.url = url;
		this.publishDate = publishDate;
		this.body = body;
		this.image_url = image_url;
		this.summary = summary;
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
	
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
		logger.debug("Tags set to: {}", tags);
	}

	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
		logger.debug("Publishing Date set to: {}", publishDate);
	}

	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
		logger.debug("Body set to: {}", body);
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
		logger.debug("Image_url set to: {}", image_url);
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", url=" + url + ", publishDate=" + publishDate + ", body="
				+ body + ", image_url=" + image_url + ", summary=" + summary + ", tags=" + tags + "]";
	}

}
