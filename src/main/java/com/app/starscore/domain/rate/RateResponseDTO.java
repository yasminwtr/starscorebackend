package com.app.starscore.domain.rate;

import java.time.LocalDate;

public record RateResponseDTO(Integer id, String userLogin, Integer movieId, Integer rating, String comment, LocalDate rateDate) {
}