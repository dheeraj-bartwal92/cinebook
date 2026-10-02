package com.cinebook.cinebook.dto.room;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoomRequest {
    @NotBlank
    @Size(max = 50)
    private String name;

    @NotNull
    @Positive
    private Integer totalRows;

    @NotNull
    @Positive
    private Integer totalSeats;
}
