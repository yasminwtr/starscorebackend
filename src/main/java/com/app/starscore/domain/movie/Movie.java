package com.app.starscore.domain.movie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "movie", schema = "starscore")
@Entity(name = "movie")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private MovieCategory movieCategory;
    
    private String title;
    private String description;
    private Integer releaseYear;
    private String director;
    private String imageUrl;
    private String trailerUrl;
    private String backgroundUrl;

}