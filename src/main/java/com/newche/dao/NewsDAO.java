package com.newche.dao;

import com.newche.model.News;
import com.newche.model.Tag;

import jakarta.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Repository
public class NewsDAO {

    private final MongoTemplate mongoTemplate;
    private static final Logger logger = LoggerFactory.getLogger(NewsDAO.class);

    @Autowired
    public NewsDAO(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // Create a news item
    public News createNews(News news) {
        try {
            logger.info("Creating a new news item with title: {}", news.getTitle());
            return mongoTemplate.save(news);
        } catch (DataAccessException e) {
            logger.error("Error occurred while creating news item: {}", e.getMessage());
            return null;
        }
    }

    // Find news by ID
    public News findNewsById(String id) {
        try {
            logger.info("Finding news by ID: {}", id);
            return mongoTemplate.findById(id, News.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while finding news by ID: {}", e.getMessage());
            return null;
        }
    }

    // Find news by tags and date
    public List<News> findNewsByTagsAndDate(List<Tag> tags, Date date) {
        try {
            logger.info("Finding news by tags and date");
            Query query = new Query();
            query.addCriteria(Criteria.where("tags.$id").in(tags).and("publishDate").is(date));
            return mongoTemplate.find(query, News.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while finding news by tags and date: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    // Delete a news item
    public void deleteNews(String id) {
        try {
            logger.info("Deleting news with ID: {}", id);
            Query query = new Query(Criteria.where("id").is(id));
            mongoTemplate.remove(query, News.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while deleting news: {}", e.getMessage());
        }
    }

    // Find news by tags
    public List<News> findNewsByTags(List<String> tagIds) {
        try {
            logger.info("Finding news by tags");
            Query query = new Query();
            query.addCriteria(Criteria.where("tags.$id").in(tagIds));
            return mongoTemplate.find(query, News.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while finding news by tags: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    // Find news by date
    public List<News> findNewsByDate(Date date) {
        try {
            logger.info("Finding news by date");
            Query query = new Query();
            query.addCriteria(Criteria.where("publishDate").is(date));
            return mongoTemplate.find(query, News.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while finding news by date: {}", e.getMessage());
            return Collections.emptyList();
        }
    }
    
    
    // Get all news
	public List<News> findAllNews() {
		try {
            logger.info("Listing all news items");
            return mongoTemplate.findAll(News.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while listing all news items: {}", e.getMessage());
            return Collections.emptyList();
        }
	}
    
	@PreDestroy
	public void destroy() {
		logger.info("News DAO is destroyed.");
		
	}


}
