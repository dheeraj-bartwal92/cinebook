package com.cinebook.cinebook.dto.theater;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TheaterRequest {
    @NotBlank
    @Size(min = 5, max = 255)
    private String name;

    @NotBlank
    @Size(max = 500)
    private String address;

    @NotBlank
    @Size(max = 100)
    private String city;
}
