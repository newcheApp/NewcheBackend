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

import java.util.Calendar;
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

    // Find news by tags
    public List<News> findNewsByTags(List<String> tagIds) {
        try {
            logger.info("Finding news by tags");
            Query query = new Query();
            query.addCriteria(Criteria.where("tags").in(tagIds));  // Adjusted to match an array of strings
            return mongoTemplate.find(query, News.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while finding news by tags: {}", e.getMessage());
            return Collections.emptyList();
        }
    }
    

    // Find news by date
    public List<News> findNewsByDate(Date date) {
    try {
        // Reset the time part of the date to get the start of the day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date startDate = calendar.getTime(); // Start of the day
        
        // Move the calendar to the end of the day
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endDate = calendar.getTime(); // Start of the next day

        logger.info("Finding news from {} to {}", startDate, endDate);
        Query query = new Query();
        query.addCriteria(Criteria.where("date").gte(startDate).lt(endDate));

        return mongoTemplate.find(query, News.class);
    } catch (DataAccessException e) {
        logger.error("Error occurred while finding news by date: {}", e.getMessage());
        return Collections.emptyList();
    }
}

    // Find news by tags and date
    public List<News> findNewsByTagsAndDate(List<String> tagIds, Date date) {
        try {
            logger.info("Finding news by tags: {} and date: {}", tagIds, date);

            // Set up the date range to cover the entire day
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            Date startDate = calendar.getTime(); // start of the day
            calendar.add(Calendar.DATE, 1);
            Date endDate = calendar.getTime(); // start of the next day

            logger.info("Querying for news items from {} to {}", startDate, endDate);

            // Build the query to filter by tags and the date range
            Query query = new Query();
            query.addCriteria(Criteria.where("tags").in(tagIds)
                                      .andOperator(
                                          Criteria.where("publishDate").gte(startDate).lt(endDate)
                                      ));

            List<News> results = mongoTemplate.find(query, News.class);
            logger.info("Query returned {} news items", results.size());

            return results;
        } catch (DataAccessException e) {
            logger.error("Error occurred while finding news by tags and date: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

	@PreDestroy
	public void destroy() {
		logger.info("News DAO is destroyed.");
		
	}


}
