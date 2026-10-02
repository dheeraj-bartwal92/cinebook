package com.cinebook.cinebook.repository;

import com.cinebook.cinebook.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
    List<Theater> findByCity(String city);
}
