package com.cinebook.cinebook.service;

import com.cinebook.cinebook.dto.room.RoomRequest;
import com.cinebook.cinebook.dto.room.RoomResponse;
import com.cinebook.cinebook.dto.theater.TheaterResponse;
import com.cinebook.cinebook.entity.Room;
import com.cinebook.cinebook.entity.Theater;
import com.cinebook.cinebook.repository.RoomRepository;
import com.cinebook.cinebook.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final TheaterRepository theaterRepository;

    public RoomResponse createRoom(Long theaterId, RoomRequest request) {
        Theater theater = theaterRepository.findById(theaterId).orElseThrow(() -> new RuntimeException("Theater not found"));
        Room room = new Room();
        room.setTheater(theater);
        room.setName(request.getName());
        room.setTotalRows(request.getTotalRows());
        room.setTotalSeats(request.getTotalSeats());

        Room saved = roomRepository.save(room);
        return mapToResponse(saved);
    }

    public RoomResponse getRoomById(Long id) {
        Room room = findOrThrow(id);
        return mapToResponse(room);
    }

    public RoomResponse updateRoom(Long id, RoomRequest request) {
        Room room = findOrThrow(id);
        room.setName(room.getName());
        room.setTotalRows(room.getTotalRows());
        room.setTotalSeats(room.getTotalSeats());

        Room updated = roomRepository.save(room);
        return mapToResponse(updated);
    }

    public void deleteRoom(Long id) {
        Room room = findOrThrow(id);
        roomRepository.delete(room);
    }

    private Room findOrThrow(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found: " + id));
    }

    public List<RoomResponse> getRoomsByTheater(Long theaterId) {
        return roomRepository.findByTheaterId(theaterId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    private RoomResponse mapToResponse(Room room) {
        return new RoomResponse(
                room.getId(),
                room.getName(),
                room.getTotalRows(),
                room.getTotalSeats(),
                new TheaterResponse(
                        room.getTheater().getId(),
                        room.getTheater().getName(),
                        room.getTheater().getAddress(),
                        room.getTheater().getCity()
                )
        );
    }
}
