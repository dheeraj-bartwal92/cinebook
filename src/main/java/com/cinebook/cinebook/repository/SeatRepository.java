package com.cinebook.cinebook.repository;

import com.cinebook.cinebook.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {
    List<Seat> findByRoomId(Long roomId);
    boolean existsByRoomId(Long roomId);
    long countByRoomId(Long roomId);
}
