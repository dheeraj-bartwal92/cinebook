package com.cinebook.cinebook.dto.moviecast;

import com.cinebook.cinebook.enums.CastRole;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieCastRequest {

    @NotNull
    private Long personId;

    @NotNull
    private CastRole role;

    private String characterName;
}