package com.app.starscore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.starscore.domain.movie.MovieResponseDTO;
import com.app.starscore.domain.rate.AverageReviewResponseDTO;
import com.app.starscore.domain.rate.Rate;
import com.app.starscore.domain.rate.RateResponseDTO;
import com.app.starscore.domain.watchlist.WatchedMovieDTO;
import com.app.starscore.domain.watchlist.Watchlist;
import com.app.starscore.domain.watchlist.WatchlistResponseDTO;
import com.app.starscore.services.MovieService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getMovies() {
        List<MovieResponseDTO> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/watchlist/{userId}")
    public ResponseEntity<List<WatchlistResponseDTO>> getUserWatchlist(@PathVariable Integer userId) {
        List<WatchlistResponseDTO> watchlist = movieService.getUserWatchlist(userId);
        if (watchlist != null) {
            return new ResponseEntity<>(watchlist, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/rating/{userId}")
    public ResponseEntity<List<RateResponseDTO>> getRateByUser(@PathVariable Integer userId) {
        List<RateResponseDTO> rateResponseDTOs = movieService.getRateByUser(userId);
        if (rateResponseDTOs != null) {
            return new ResponseEntity<>(rateResponseDTOs, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/movieratings/{movieId}")
    public ResponseEntity<List<RateResponseDTO>> getRateByMovie(@PathVariable Integer movieId) {
        List<RateResponseDTO> rateResponseDTOs = movieService.getRateByMovie(movieId);
        if (rateResponseDTOs != null) {
            return new ResponseEntity<>(rateResponseDTOs, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/avgrating/{movieId}")
    public ResponseEntity<AverageReviewResponseDTO> getAverageReviewByMovie(@PathVariable Integer movieId) {
        AverageReviewResponseDTO averageReview = movieService.getAverageReviewByMovie(movieId);
        if (averageReview != null) {
            return new ResponseEntity<>(averageReview, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/rating")
    public ResponseEntity<Rate> saveRate(@RequestBody Rate data) {
        Rate savedRate = movieService.saveRate(data);
        return new ResponseEntity<>(savedRate, HttpStatus.CREATED);
    }

    @PostMapping("/watchlist")
    public ResponseEntity<Watchlist> addToWatchlist(@RequestBody Watchlist data) {
        Watchlist addedWatchlist = movieService.addToWatchlist(data);
        return new ResponseEntity<>(addedWatchlist, HttpStatus.CREATED);
    }

    @PutMapping("/favorite/{watchlistId}")
    public ResponseEntity<Void> updateFavorite(@PathVariable Integer watchlistId, @RequestBody Boolean favorite) {
        movieService.updateFavorite(watchlistId, favorite);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/watched/{watchlistId}")
    public ResponseEntity<Void> updateWatched(@PathVariable Integer watchlistId, @RequestBody WatchedMovieDTO data) {
        Boolean watched = data.watched();
        LocalDate date = data.date();
        movieService.updateWatched(watchlistId, watched, date);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/rating/{rateId}")
    public ResponseEntity<Void> updateRate(@PathVariable Integer rateId, @RequestBody Map<String, String> updates) {
        if (updates.containsKey("rating")) {
            String newRatingStr = updates.get("rating");
            Integer newRating = Integer.parseInt(newRatingStr);
            movieService.updateRating(rateId, newRating);

        } else if (updates.containsKey("comment")) {
            String newComment = updates.get("comment");
            movieService.updateComment(rateId, newComment);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/watchlist/{userId}/{movieId}")
    public ResponseEntity<Void> removeFromWatchlist(@PathVariable Integer userId, @PathVariable Integer movieId) {
        movieService.removeFromWatchlist(userId, movieId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/rating/{rateId}")
    public ResponseEntity<Void> removeUser(@PathVariable Integer rateId) {
        movieService.removeRate(rateId);
        return ResponseEntity.noContent().build();
    }
}