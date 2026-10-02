package com.cinebook.cinebook.repository;

import com.cinebook.cinebook.entity.MovieCast;
import com.cinebook.cinebook.enums.CastRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieCastRepository extends JpaRepository<MovieCast, Long> {

    List<MovieCast> findByMovieId(Long movieId);

    List<MovieCast> findByPersonId(Long personId);

    boolean existsByMovieIdAndPersonIdAndRole(Long movieId, Long personId, CastRole role);
}