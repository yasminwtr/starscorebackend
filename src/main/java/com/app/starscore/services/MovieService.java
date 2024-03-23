package com.app.starscore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.starscore.domain.movie.Movie;
import com.app.starscore.domain.movie.MovieCategory;
import com.app.starscore.domain.movie.MovieResponseDTO;
import com.app.starscore.domain.rate.AverageReviewResponseDTO;
import com.app.starscore.domain.rate.Rate;
import com.app.starscore.domain.rate.RateResponseDTO;
import com.app.starscore.domain.watchlist.Watchlist;
import com.app.starscore.domain.watchlist.WatchlistResponseDTO;
import com.app.starscore.repositories.MovieCategoryRepository;
import com.app.starscore.repositories.MovieRepository;
import com.app.starscore.repositories.RateRepository;
import com.app.starscore.repositories.WatchlistRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private WatchlistRepository watchlistRepository;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private MovieCategoryRepository movieCategoryRepository;

    public List<MovieResponseDTO> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(movie -> new MovieResponseDTO(
                        movie.getId(),
                        movie.getTitle(),
                        movie.getDescription(),
                        movie.getReleaseYear(),
                        movie.getDirector(),
                        movie.getImageUrl(),
                        movie.getTrailerUrl(),
                        movie.getBackgroundUrl(),
                        movie.getMovieCategory().getName()))
                .collect(Collectors.toList());
    }

    public List<MovieCategory> getAllCategories(){
        List<MovieCategory> categories = movieCategoryRepository.findAll();
        return categories;
    }

    public List<WatchlistResponseDTO> getUserWatchlist(Integer userId) {
        List<Watchlist> watchlists = watchlistRepository.findByUserId(userId);
        return watchlists.stream()
                .map(watchlist -> new WatchlistResponseDTO(
                        watchlist.getId(),
                        watchlist.getUserId(),
                        watchlist.getMovie().getId(),
                        watchlist.getMovie().getTitle(),
                        watchlist.getMovie().getDescription(),
                        watchlist.getMovie().getReleaseYear(),
                        watchlist.getMovie().getDirector(),
                        watchlist.getMovie().getImageUrl(),
                        watchlist.getMovie().getTrailerUrl(),
                        watchlist.getMovie().getBackgroundUrl(),
                        watchlist.getMovie().getMovieCategory().getName(),
                        watchlist.getWatchedDate(),
                        watchlist.getFavorite(),
                        watchlist.getWatched()))
                .collect(Collectors.toList());
    }

    public List<RateResponseDTO> getRateByUser(Integer userId) {
        List<Rate> rates = rateRepository.findByUserId(userId);
        return rates.stream()
                .map(rate -> new RateResponseDTO(
                        rate.getId(),
                        rate.getUser().getLogin(),
                        rate.getMovieId(),
                        rate.getRating(),
                        rate.getComment(),
                        rate.getRateDate()))
                .collect(Collectors.toList());
    }

    public List<RateResponseDTO> getRateByMovie(Integer movieId) {
        List<Rate> rates = rateRepository.findByMovieId(movieId);
        return rates.stream()
                .map(rate -> new RateResponseDTO(
                        rate.getId(),
                        rate.getUser().getLogin(),
                        rate.getMovieId(),
                        rate.getRating(),
                        rate.getComment(),
                        rate.getRateDate()))
                .collect(Collectors.toList());
    }

    public AverageReviewResponseDTO getAverageReviewByMovie(Integer movieId){
        return rateRepository.findAverageReviewByMovieId(movieId);
    }

    public Rate saveRate(Rate rate){
        return rateRepository.save(rate);
    }

    public Watchlist addToWatchlist(Watchlist watchlist) {
        return watchlistRepository.save(watchlist);
    }

    public void updateFavorite(Integer watchlistId, Boolean favorite) {
        Watchlist watchlist = watchlistRepository.findById(watchlistId).orElse(null);
        if (watchlist != null) {
            watchlist.setFavorite(favorite);
            watchlistRepository.save(watchlist);
        } else {
            throw new IllegalArgumentException("Watchlist with ID " + watchlistId + " not found");
        }
    }

    public void updateWatched(Integer watchlistId, Boolean watched, LocalDate date) {
        Watchlist watchlist = watchlistRepository.findById(watchlistId).orElse(null);
        if (watchlist != null) {
            watchlist.setWatched(watched);
            watchlist.setWatchedDate(date);
            watchlistRepository.save(watchlist);
        } else {
            throw new IllegalArgumentException("Watchlist with ID " + watchlistId + " not found");
        }
    }

    public void updateRating(Integer rateId, Integer rating) {
        Rate rate = rateRepository.findById(rateId).orElse(null);
        if (rate != null) {
            rate.setRating(rating);
            rateRepository.save(rate);
        } else {
            throw new IllegalArgumentException("User with ID " + rateId + " not found");
        }
    }

    public void updateComment(Integer rateId, String comment) {
        Rate rate = rateRepository.findById(rateId).orElse(null);
        if (rate != null) {
            rate.setComment(comment);
            rateRepository.save(rate);
        } else {
            throw new IllegalArgumentException("User with ID " + rateId + " not found");
        }
    }

    public void removeFromWatchlist(Integer userId, Integer movieId) {
        watchlistRepository.deleteByUserIdAndMovieId(userId, movieId);
    }

    public void removeRate(Integer rateId) {
        rateRepository.deleteById(rateId);
    }

}