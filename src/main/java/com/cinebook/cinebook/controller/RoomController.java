package com.cinebook.cinebook.controller;

import com.cinebook.cinebook.dto.room.RoomRequest;
import com.cinebook.cinebook.dto.room.RoomResponse;
import com.cinebook.cinebook.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/api/theaters/{theaterId}/rooms")
    public ResponseEntity<List<RoomResponse>> getRoomsByTheater(@PathVariable Long theaterId) {
        return ResponseEntity.ok(roomService.getRoomsByTheater(theaterId));
    }

    @GetMapping("/api/rooms/{id}")
    public ResponseEntity<RoomResponse> getRoom(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @PostMapping("/api/theaters/{theaterId}/rooms")
    public ResponseEntity<RoomResponse> createRoom(@PathVariable Long theaterId, @Valid @RequestBody RoomRequest request) {
        RoomResponse response = roomService.createRoom(theaterId,request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/api/rooms/{id}")
    public ResponseEntity<RoomResponse> updateRoom(@PathVariable Long id, @Valid @RequestBody RoomRequest request ) {
        return ResponseEntity.ok(roomService.updateRoom(id, request));
    }

    @DeleteMapping("/api/rooms/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }

}
