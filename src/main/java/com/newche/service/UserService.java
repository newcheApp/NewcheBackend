package com.newche.service;

import com.newche.dao.UserDAO;
import com.newche.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // Create a new user with enhanced error handling
    public User createUser(User user) {
        try {
            User createdUser = userDAO.createUser(user);
            if (createdUser == null) {
                logger.error("Failed to create a new user with email: {}", user.getEmail());
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User creation failed.");
            }
            logger.info("Successfully created a new user with ID: {}", createdUser.getId());
            return createdUser;
        } catch (Exception e) {
            logger.error("Exception occurred while creating user: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error during user creation.", e);
        }
    }

    // Find user by ID with error handling
    public User findUserById(String id) {
        try {
            User user = userDAO.findUserById(id);
            if (user == null) {
                logger.warn("No user found with ID: {}", id);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
            }
            logger.info("Found user with ID: {}", id);
            return user;
        } catch (Exception e) {
            logger.error("Exception occurred while fetching user by ID: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error during user retrieval.", e);
        }
    }

    public User findUserByEmail(String email) {
        try {
            User user = userDAO.findUserByEmail(email);
            if (user == null) {
                logger.warn("No user found with email: {}", email);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
            }
            logger.info("Found a user with email: {}", email);
            return user;
        } catch(Exception e) {
            logger.error("Exception occurred while fetching user by email: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error during user retrieval.", e);
        }
    }

    // Validate user credentials
    public boolean validateUser(String identifier, String password) {
        try {
            boolean isValid = identifier.contains("@") ?
                userDAO.validateUserByEmail(identifier, password) != null :
                userDAO.validateUserByUsername(identifier, password) != null;
            logger.info("Credentials validation for {}: {}", identifier, isValid ? "valid" : "invalid");
            return isValid;
        } catch (Exception e) {
            logger.error("Validation error for {}: {}", identifier, e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during credential validation.", e);
        }
    }

    // Update user profile
    public User updateUserProfile(User updatedUser) {
        try {
            User user = userDAO.updateUser(updatedUser.getId(), updatedUser);
            if (user == null) {
                logger.error("Failed to update user profile for ID: {}", updatedUser.getId());
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User update failed.");
            }
            logger.info("Successfully updated user profile for ID: {}", updatedUser.getId());
            return user;
        } catch (Exception e) {
            logger.error("Exception occurred while updating user profile: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error during user update.", e);
        }
    }

    // Change user password
    public void changeUserPassword(String userId, String newPassword) {
        try {
            userDAO.updateUserPassword(userId, newPassword);
            logger.info("Password changed for user ID: {}", userId);
        } catch (Exception e) {
            logger.error("Exception occurred while changing password for user ID: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error changing password.", e);
        }
    }

    // Edit user tags
    public void editUserTags(String userId, List<String> newTags) {
        try {
            userDAO.updateUserTags(userId, newTags);
            logger.info("Tags updated for user ID: {}", userId);
        } catch (Exception e) {
            logger.error("Exception occurred while updating tags for user ID: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating user tags.", e);
        }
    }

    // Delete a user
    public void deleteUser(String id) {
        try {
            userDAO.deleteUser(id);
            logger.info("User deleted with ID: {}", id);
        } catch (Exception e) {
            logger.error("Exception occurred while deleting user ID: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting user.", e);
        }
    }

    // Fetch all users
    public List<User> getAllUsers() {
        try {
            List<User> users = userDAO.findAllUsers();
            if (users.isEmpty()) {
                logger.info("No users found");
            } else {
                logger.info("Retrieved {} users", users.size());
            }
            return users;
        } catch (Exception e) {
            logger.error("Exception occurred while retrieving all users: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving users.", e);
        }
    }
}
