package com.app.starscore.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.starscore.services.UserService;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateAccount(@PathVariable Integer userId, @RequestBody Map<String, String> updates) {
        if (updates.containsKey("login")) {
            String newLogin = updates.get("login");
            userService.updateLogin(userId, newLogin);

        } else if (updates.containsKey("password")) {
            String newPassword = updates.get("password");
            String encryptedPassword = new BCryptPasswordEncoder().encode(newPassword);
            userService.updatePassword(userId, encryptedPassword);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> removeUser(@PathVariable Integer userId) {
        userService.removeUser(userId);
        return ResponseEntity.noContent().build();
    }
}