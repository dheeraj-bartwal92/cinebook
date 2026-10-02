package com.cinebook.cinebook.dto.moviecast;

import com.cinebook.cinebook.dto.person.PersonResponse;
import com.cinebook.cinebook.enums.CastRole;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieCastResponse {
    private Long id;
    private PersonResponse person;
    private CastRole role;
    private String characterName;
}