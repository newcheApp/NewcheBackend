package com.newche.controller;

import com.newche.model.User;
import com.newche.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users") // Base path for all endpoints in this controller
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create a new user
    @CrossOrigin
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("Attempting to create user: {}", user.getEmail());
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Retrieve all users
    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Fetching all users");
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Retrieve a user by ID
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        logger.info("Fetching user by ID: {}", id);
        User user = userService.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            logger.warn("No user found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        logger.info("Fetching user by email: {}", email);
        User user = userService.findUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            logger.warn("No user found with email: {}", email);
            return ResponseEntity.notFound().build();
        }
    }


    // Update a user
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        logger.info("Updating user with ID: {}", id);
        User updatedUser = userService.updateUserProfile(user);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete a user
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        logger.info("Deleting user with ID: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
// Helper class to encapsulate user credentials
class UserCredentials {
    private String identifier; // This could be either email or username
    private String password;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}