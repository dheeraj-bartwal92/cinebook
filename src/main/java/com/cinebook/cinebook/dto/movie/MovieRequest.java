package com.cinebook.cinebook.dto.movie;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class MovieRequest {
    @NotBlank
    private String title;

    private String description;

    @NotNull
    @Positive
    private Integer durationMinutes;

    @NotNull
    private LocalDate releaseDate;

    @Size(max = 10)
    private String rating;
    private String posterUrl;

    @NotEmpty
    private Set<Long> genreIds;
}
