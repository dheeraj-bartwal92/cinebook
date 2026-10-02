package com.cinebook.cinebook.dto.movie;

import com.cinebook.cinebook.enums.MovieStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieStatusUpdateRequest {

    @NotNull
    private MovieStatus status;
}