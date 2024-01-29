package com.app.starscore.domain.watchlist;

import java.time.LocalDate;

import com.app.starscore.domain.movie.Movie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Table(name = "watchlist", schema = "starscore")
@Entity(name = "watchlist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "watched_date")
    private LocalDate watchedDate;

    private Boolean favorite;
    private Boolean watched;

}