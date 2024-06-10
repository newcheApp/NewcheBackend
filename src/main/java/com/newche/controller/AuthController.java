package com.newche.controller;
import com.newche.model.UserCredentials;
import com.newche.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:5173") // Allow requests from your frontend URL
    @PostMapping("/validate-credentials")
    public ResponseEntity<Boolean> validateCredentials(@RequestBody UserCredentials credentials) {
        logger.info("Validation service called for identifier: {}", credentials.getIdentifier());
        boolean isValid = userService.validateUser(credentials.getIdentifier(), credentials.getPassword());
        logger.info("Validation response for {}: {}", credentials.getIdentifier(), isValid ? "successful" : "unsuccessful");
        return ResponseEntity.ok(isValid);
    }
}
