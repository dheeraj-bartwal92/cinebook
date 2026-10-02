package com.cinebook.cinebook.dto.theater;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TheaterResponse {
    private Long id;
    private String name;
    private String address;
    private String city;
}
