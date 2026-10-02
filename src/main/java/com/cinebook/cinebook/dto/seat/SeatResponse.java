package com.cinebook.cinebook.dto.seat;

import com.cinebook.cinebook.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeatResponse {
    private Long id;
    private String rowLabel;
    private Integer seatNumber;
    private SeatType seatType;
}
