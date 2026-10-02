package com.cinebook.cinebook.dto.movie;

import com.cinebook.cinebook.dto.genre.GenreResponse;
import com.cinebook.cinebook.enums.MovieStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
public class MovieResponse {
    private Long id;
    private String title;
    private String description;
    private Integer durationMinutes;
    private LocalDate releaseDate;
    private String rating;
    private String posterUrl;
    private MovieStatus status;
    private Set<GenreResponse> genres;
}