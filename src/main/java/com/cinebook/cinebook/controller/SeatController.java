package com.cinebook.cinebook.controller;

import com.cinebook.cinebook.dto.seat.SeatBulkCreateRequest;
import com.cinebook.cinebook.dto.seat.SeatResponse;
import com.cinebook.cinebook.service.SeatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

    @GetMapping("/api/rooms/{roomId}/seats")
    public ResponseEntity<List<SeatResponse>> getSeatsByRoom(@PathVariable Long roomId) {
        return ResponseEntity.ok(seatService.getSeatsByRoom(roomId));
    }

    @PostMapping("/api/rooms/{roomId}/seats/generate")
    public ResponseEntity<List<SeatResponse>> generateSeats(@PathVariable Long roomId, @Valid @RequestBody SeatBulkCreateRequest request) {
        List<SeatResponse> response = seatService.generateSeats(roomId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/seats/{id}")
    public ResponseEntity<Void> deleteSeat(@PathVariable Long id) {
        seatService.deleteSeat(id);
        return ResponseEntity.noContent().build();
    }
}
