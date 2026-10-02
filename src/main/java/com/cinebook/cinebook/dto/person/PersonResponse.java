package com.cinebook.cinebook.dto.person;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonResponse {
    private Long id;
    private String name;
    private String photoUrl;
    private String bio;
}