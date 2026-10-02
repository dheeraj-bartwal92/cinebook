package com.cinebook.cinebook.dto.genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class GenreRequest {
    @NotEmpty
    private List<String> names;
}
