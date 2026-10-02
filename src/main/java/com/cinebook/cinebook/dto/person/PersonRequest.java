package com.cinebook.cinebook.dto.person;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PersonRequest {
    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 500)
    private String photoUrl;

    private String bio;
}
