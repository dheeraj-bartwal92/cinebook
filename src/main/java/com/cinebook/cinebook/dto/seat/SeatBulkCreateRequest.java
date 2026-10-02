package com.cinebook.cinebook.dto.seat;

import com.cinebook.cinebook.enums.SeatType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SeatBulkCreateRequest {
    @NotEmpty
    @Valid
    private List<RowSpec> rows;

    @Data
    public static class RowSpec {
        @NotNull
        private String rowLabel;

        @NotNull
        private Integer seatCount;

        @NotNull
        private SeatType seatType;
    }
}
