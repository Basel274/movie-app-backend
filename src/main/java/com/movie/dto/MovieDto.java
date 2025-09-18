package com.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Integer movieId;

    @NotBlank(message = "please enter a movie's title")
    private String title;

    @NotBlank(message = "please enter a movie's director")
    private String director;

    @NotBlank(message = "please enter a movie's studio")
    private String studio;

    private Set<String> movieCast;

    private Integer year;

    @NotBlank(message = "please enter a movie's poster")
    private String poster;

    @NotBlank(message = "please enter a poster's's URL")
    private String posterUrl;
}
