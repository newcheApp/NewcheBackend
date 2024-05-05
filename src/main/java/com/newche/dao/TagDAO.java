package com.newche.dao;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.newche.model.Tag;
import com.newche.model.User;

import jakarta.annotation.PreDestroy;

@Repository
public class TagDAO {

	private final MongoTemplate mongoTemplate;
    private static final Logger logger = LoggerFactory.getLogger(TagDAO.class);
    
    @Autowired
    public TagDAO (MongoTemplate mongoTemplate) {
    	this.mongoTemplate = mongoTemplate;
    }
    
    public Tag createTag(Tag tag) {
    	try {
    		logger.info("Creating a new Tag with name: {}", tag.getName());
            return mongoTemplate.save(tag);
		} catch (DataAccessException e) {
			logger.error("ERROR - CAN NOT create a new Tag with name: {}", tag.getName());
			return null;
		}
    }
    
    public Tag findTagById(String id){
        try {
            logger.info("Finding Tag by ID: {}", id);
            return mongoTemplate.findById(id, Tag.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while finding Tag by ID: {}", e.getMessage());
            return null;
        }
    }
    
    public List<Tag> findTagsByLevel(int level){
        try {
            logger.info("Finding Tag by level: {}", level);
            Query query = new Query(Criteria.where("level").is(level));
            return mongoTemplate.find(query, Tag.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while finding Tag by ID: {}", e.getMessage());
            return null;
        }
    }
    
    
    public Tag findTagByName(String name){
        try {
            logger.info("Finding tag by name: {}", name);
            Query query = new Query(Criteria.where("name").is(name));
            return mongoTemplate.findOne(query, Tag.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while finding tag by name: {}", e.getMessage());
            return null;
        }
    }

    
    public List<Tag> findAllTags(){
    	try {
            logger.info("Listing all tags");
            return mongoTemplate.findAll(Tag.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while listing all Tags: {}", e.getMessage());
            return Collections.emptyList();
        }
    }
    
    public void deleteTag(String id) {
        try {
            logger.info("Deleting Tag with ID: {}", id);
            Query query = new Query(Criteria.where("id").is(id));
            mongoTemplate.remove(query, Tag.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while deleting Tag: {}", e.getMessage());
        }
    }
    
	@PreDestroy
	public void destroy() {
		logger.info("Tag DAO is destroyed.");
		
	}
    
}
