package com.cinebook.cinebook.repository;

import com.cinebook.cinebook.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByTheaterId(Long theaterId);
    boolean existsByTheaterId(Long theaterId);
}
