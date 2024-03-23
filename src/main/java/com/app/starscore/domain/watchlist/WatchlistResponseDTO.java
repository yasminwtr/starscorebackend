package com.app.starscore.domain.watchlist;

import java.time.LocalDate;

public record WatchlistResponseDTO(Integer id, Integer userId, Integer movieId, String title, String description, Integer releaseYear, String director,
        String imageUrl, String trailerUrl, String backgroundUrl, String categoryName, LocalDate watchedDate, Boolean favorite, Boolean watched) {
}