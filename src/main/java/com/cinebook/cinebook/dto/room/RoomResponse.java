package com.cinebook.cinebook.dto.room;

import com.cinebook.cinebook.dto.theater.TheaterResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomResponse {
    private Long id;
    private String name;
    private Integer totalRows;
    private Integer totalSeats;
    private TheaterResponse theater;
}
