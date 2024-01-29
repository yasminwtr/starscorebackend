package com.app.starscore.domain.user;

public record RegisterDTO(String login, String password, UserRole role) {
}