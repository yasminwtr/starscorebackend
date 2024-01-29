package com.app.starscore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.starscore.domain.rate.Rate;

public interface RateRepository extends JpaRepository<Rate, Integer>{
    @Query("SELECT r FROM rate r INNER JOIN FETCH r.user u WHERE r.user.id = :userId")
    List<Rate> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT r FROM rate r WHERE r.movieId = :movieId")
    List<Rate> findByMovieId(@Param("movieId") Integer movieId);

    void deleteById(Integer rateId);
}