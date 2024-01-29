package com.app.starscore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.starscore.domain.user.User;
import com.app.starscore.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void updateLogin(Integer userId, String login) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setLogin(login);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }
    }

    public void updatePassword(Integer userId, String password) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setPassword(password);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }
    }

    public void removeUser(Integer userId) {
        userRepository.deleteById(userId);
    }
}