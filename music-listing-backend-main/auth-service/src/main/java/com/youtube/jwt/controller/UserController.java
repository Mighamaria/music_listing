package com.youtube.jwt.controller;

import com.youtube.jwt.dto.UserRegistrationRequest;
import com.youtube.jwt.entity.User;
import com.youtube.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registerNewUser")
    public ResponseEntity<?> registerNewUser(@RequestBody @Valid UserRegistrationRequest userRegistrationRequest) {
        // Check if the username is already registered
        if (userService.findByUserName(userRegistrationRequest.getUserName()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        // Register a new user
        User registeredUser = userService.registerNewUser(userRegistrationRequest);
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/forAdmin")
    @PreAuthorize("hasRole('ROLE_Admin')")
    public ResponseEntity<String> forAdmin() {
        return ResponseEntity.ok("This URL is only accessible to the admin");
    }

    @GetMapping("/forUser")
    @PreAuthorize("hasRole('ROLE_User')")
    public ResponseEntity<String> forUser() {
        return ResponseEntity.ok("This URL is only accessible to the user");
    }
}
