package com.app.starscore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.starscore.domain.movie.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>{
}