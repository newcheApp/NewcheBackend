package com.newche.service;

import com.newche.dao.*;
import com.newche.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // Create a new user
    public User createUser(User user) {
        // Additional logic before saving the user
        return userDAO.createUser(user);
    }

    // Find User by id
    public User findUserById(String id) {
    	User user = userDAO.findUserById(id);
    	return user;
    }
    
    // Validate user credentials
    public boolean validateUser(String username, String password) {
        User user = userDAO.validateUserByUsername(username, password);
        return user != null;
    }

    // Update user profile
    public User updateUserProfile(User updatedUser) {
        userDAO.updateUser(updatedUser.getId(), updatedUser);
		return updatedUser;
    }

    // Change user password
    public void changeUserPassword(String userId, String newPassword) {
        userDAO.updateUserPassword(userId, newPassword);
    }

    // Edit user tags (add/delete)
    public void editUserTags(String userId, List<String> newTagIds) {
        userDAO.updateUserTags(userId, newTagIds);
    }
    
    public void deleteUser(String id) {
    	userDAO.deleteUser(id);
    }

	public List<User> getAllUsers() {
		return userDAO.findAllUsers();
	}
   
}



