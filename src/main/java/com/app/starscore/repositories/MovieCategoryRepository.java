package com.app.starscore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.starscore.domain.movie.MovieCategory;

public interface MovieCategoryRepository extends JpaRepository<MovieCategory, Integer>{
}