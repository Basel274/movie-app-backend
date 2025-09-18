package com.movie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Movie {

    //I used the reference type not the primitive type because it easily with validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(nullable = false, length = 250)
    @NotBlank(message = "please enter a movie's title")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "please enter a movie's director")
    private String director;

    @Column(nullable = false)
    @NotBlank(message = "please enter a movie's studio")
    private String studio;

    @ElementCollection
    @CollectionTable(name = "movie_cast")
    private Set<String> movieCast;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    @NotBlank(message = "please enter a movie's poster")
    private String poster;
}
