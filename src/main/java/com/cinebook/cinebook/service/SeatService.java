package com.cinebook.cinebook.service;

import com.cinebook.cinebook.dto.seat.SeatBulkCreateRequest;
import com.cinebook.cinebook.dto.seat.SeatResponse;
import com.cinebook.cinebook.entity.Room;
import com.cinebook.cinebook.entity.Seat;
import com.cinebook.cinebook.repository.RoomRepository;
import com.cinebook.cinebook.repository.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public List<SeatResponse> generateSeats(Long roomId, SeatBulkCreateRequest request) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));

        if(seatRepository.existsByRoomId(roomId)) {
            throw new RuntimeException("Seats already exist for this room. Delete existing seats before regenerating.");
        }
        List<Seat> seats = new ArrayList<>();

        for (SeatBulkCreateRequest.RowSpec rowSpec : request.getRows()) {
            for (int seatNumber = 1; seatNumber <= rowSpec.getSeatCount(); seatNumber++) {
                Seat seat = new Seat();
                seat.setRoom(room);
                seat.setRowLabel(rowSpec.getRowLabel());
                seat.setSeatNumber(seatNumber);
                seat.setSeatType(rowSpec.getSeatType());
                seats.add(seat);
            }
        }

        List<Seat> saved = seatRepository.saveAll(seats);
        room.setTotalSeats(saved.size());
        roomRepository.save(room);

        return saved.stream().map(this::mapToResponse).toList();
    }

    public List<SeatResponse> getSeatsByRoom(Long roomId) {
        return seatRepository.findByRoomId(roomId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    public void deleteSeat(Long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found: " + id));
        seatRepository.delete(seat);
    }

    private SeatResponse mapToResponse(Seat seat) {
        return new SeatResponse(
                seat.getId(),
                seat.getRowLabel(),
                seat.getSeatNumber(),
                seat.getSeatType()
        );
    }
}
