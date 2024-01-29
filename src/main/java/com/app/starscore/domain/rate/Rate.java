package com.app.starscore.domain.rate;

import java.time.LocalDate;

import com.app.starscore.domain.user.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "rate", schema = "starscore")
@Entity(name = "rate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "movie_id")
    private Integer movieId;

    private Integer rating;
    private String comment;

    @Column(name = "rate_date")
    private LocalDate rateDate;
}