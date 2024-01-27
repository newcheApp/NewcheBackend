package com.newche.dao;

import com.newche.model.*;

import jakarta.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDAO {

    private final MongoTemplate mongoTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    @Autowired
    public UserDAO(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // Adding a user
    public User createUser(User user) {
        try {
            logger.info("Creating a new user with email: {}", user.getEmail());
            return mongoTemplate.save(user);
        } catch (DataAccessException e) {
            logger.error("Error occurred while creating user: {}", e.getMessage());
            return null;
        }
    }

    // Find user by ID
    public User findUserById(String id) {
        try {
            logger.info("Finding user by ID: {}", id);
            return mongoTemplate.findById(id, User.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while finding user by ID: {}", e.getMessage());
            return null;
        }
    }

    // List all users
    public List<User> findAllUsers() {
        try {
            logger.info("Listing all users");
            return mongoTemplate.findAll(User.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while listing all users: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    // Find user by criteria
    public List<User> findUsersByCriteria(Criteria criteria) {
        try {
            logger.info("Finding users by criteria");
            Query query = new Query(criteria);
            return mongoTemplate.find(query, User.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while finding users by criteria: {}", e.getMessage());
            return Collections.emptyList();
        }
    }
    
    // Helper method to update a single field
    private void updateField(String userId, String fieldName, Object fieldValue) {
        Query query = new Query(Criteria.where("id").is(userId));
        Update update = new Update().set(fieldName, fieldValue);
        mongoTemplate.updateFirst(query, update, User.class);
    }

    // Update user information
    public void updateUser(String id, User user) {
        try {
            logger.info("Updating user with ID: {}", id);
            mongoTemplate.save(user);
        } catch (DataAccessException e) {
            logger.error("Error occurred while updating user: {}", e.getMessage());
        }
    }

    // Update user's tag list
    public void updateUserTags(String userId, List<String> tagIds) {
        try {
            logger.info("Updating tags for user with ID: {}", userId);
            Query query = new Query(Criteria.where("id").is(userId));
            Update update = new Update().set("tagIds", tagIds);
            mongoTemplate.updateFirst(query, update, User.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while updating user tags: {}", e.getMessage());
        }
    }

    // Delete a user
    public void deleteUser(String id) {
        try {
            logger.info("Deleting user with ID: {}", id);
            Query query = new Query(Criteria.where("id").is(id));
            mongoTemplate.remove(query, User.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while deleting user: {}", e.getMessage());
        }
    }

    // Validate user by username and password
    public User validateUserByUsername(String username, String password) {
        try {
            logger.info("Validating user by username: {}", username);
            Query query = new Query(Criteria.where("username").is(username).and("password").is(password));
            return mongoTemplate.findOne(query, User.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while validating user by username: {}", e.getMessage());
            return null;
        }
    }

    // Validate user by email and password
    public User validateUserByEmail(String email, String password) {
        try {
            logger.info("Validating user by email: {}", email);
            Query query = new Query(Criteria.where("email").is(email).and("password").is(password));
            return mongoTemplate.findOne(query, User.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while validating user by email: {}", e.getMessage());
            return null;
        }
    }
    
    // Find users with specific tags
    public List<User> findUsersByTags(List<String> tagIds) {
        try {
            logger.info("Finding users by tags");
            Query query = new Query(Criteria.where("tagIds").in(tagIds));
            return mongoTemplate.find(query, User.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while finding users by tags: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    // Find users by role
    public List<User> findUsersByRole(String role) {
        try {
            logger.info("Finding users by role: {}", role);
            Query query = new Query(Criteria.where("role").is(role));
            return mongoTemplate.find(query, User.class);
        } catch (DataAccessException e) {
            logger.error("Error occurred while finding users by role: {}", e.getMessage());
            return Collections.emptyList();
        }
    }
    
    public void updateUserName(String userId, String newUserName) {
        try {
            updateField(userId, "userName", newUserName);
            logger.info("Updated username for user ID: {}", userId);
        } catch (DataAccessException e) {
            logger.error("Error updating username for user ID {}: {}", userId, e.getMessage());
        }
    }

    // Update User's email
    public void updateUserEmail(String userId, String newEmail) {
        try {
            updateField(userId, "email", newEmail);
            logger.info("Updated email for user ID: {}", userId);
        } catch (DataAccessException e) {
            logger.error("Error updating email for user ID {}: {}", userId, e.getMessage());
        }
    }

    // Update User's password
    public void updateUserPassword(String userId, String newPassword) {
        try {
            updateField(userId, "password", newPassword);
            logger.info("Updated password for user ID: {}", userId);
        } catch (DataAccessException e) {
            logger.error("Error updating password for user ID {}: {}", userId, e.getMessage());
        }
    }

    // Update User's name and surname
    public void updateUserNameAndSurname(String userId, String newName, String newSurname) {
        Query query = new Query(Criteria.where("id").is(userId));
        Update update = new Update().set("name", newName).set("surname", newSurname);
        try {
            mongoTemplate.updateFirst(query, update, User.class);
            logger.info("Updated name and surname for user ID: {}", userId);
        } catch (DataAccessException e) {
            logger.error("Error updating name and surname for user ID {}: {}", userId, e.getMessage());
        }
    }
    
    
	@PreDestroy
	public void destroy() {
		logger.info("User DAO is destroyed.");
		
	}

}


