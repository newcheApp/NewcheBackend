package com.newche.dao;
import com.newche.model.News;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;
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

    // Get all with reverse order
    public List<News> findAllReverseOrder() {
        try{
            logger.info("Listing all news items in reverse");
            // Create a query object
            Query query = new Query();
            // Sort by createdAt field in descending order
            query.with(Sort.by(Sort.Direction.DESC, "date"));
            // Execute the query to get all news in reverse order
            return mongoTemplate.find(query, News.class);

        }catch(DataAccessException e) {
            logger.error("Error occured while listing all news in reverse order: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    // Get a specific number of news items in reverse order
    public List<News> findNewsInRange(int start, int count) {
        try {
            logger.info("Listing news items from {} to {} in reverse", start, start + count);
            // Create a query object
            Query query = new Query();
            // Sort by createdAt field in descending order
            query.with(Sort.by(Sort.Direction.DESC, "date"));
            // Skip the first 'start' items and limit the result to 'count' items
            query.skip(start).limit(count);
            // Execute the query to get the specified range of news in reverse order
            return mongoTemplate.find(query, News.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while listing news items in range {} to {}: {}", start, start + count, e.getMessage());
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
            query.addCriteria(Criteria.where("tags").in(tagIds));
            query.with(Sort.by(Sort.Direction.DESC, "date")); // Assuming 'date' is the field name
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

    // Find news by tags and date - using intersection of results
// Find news by tags and date with enhanced logging
    public List<News> findNewsByTagsAndDate(List<String> tagIds, Date date) {
        try {
            logger.info("Finding news by tags: {} and date: {}", tagIds, date);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            Date startDate = calendar.getTime(); // Start of the day
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Date endDate = calendar.getTime(); // Start of the next day

            Query query = new Query();
            query.addCriteria(Criteria.where("date").gte(startDate).lt(endDate)
                            .andOperator(Criteria.where("tags").in(tagIds)));

            logger.debug("Constructed query: {}", query.toString());
            List<News> results = mongoTemplate.find(query, News.class);
            logger.info("Query returned {} results.", results.size());

            return results;
        } catch (DataAccessException e) {
            logger.error("Error occurred while finding news by tags and date: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
}

	@PreDestroy
	public void destroy() {
		logger.info("News DAO is destroyed.");
		
	}


}
