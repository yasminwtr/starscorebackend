package com.app.starscore.domain.movie;

public record MovieResponseDTO(Integer id, String title, String description, Integer releaseYear, String director,
        String imageUrl, String trailerUrl, String backgroundUrl, String categoryName) {
}