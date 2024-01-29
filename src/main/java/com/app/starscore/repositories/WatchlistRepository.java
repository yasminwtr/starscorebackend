package com.app.starscore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.starscore.domain.watchlist.Watchlist;

public interface WatchlistRepository extends JpaRepository<Watchlist, Integer>{
    @Query("SELECT w FROM watchlist w INNER JOIN FETCH w.movie m WHERE w.userId = :userId")
    List<Watchlist> findByUserId(@Param("userId") Integer userId);

    boolean existsByUserIdAndMovieId(Integer userId, Integer movieId);

    void deleteByUserIdAndMovieId(Integer userId, Integer movieId);
}