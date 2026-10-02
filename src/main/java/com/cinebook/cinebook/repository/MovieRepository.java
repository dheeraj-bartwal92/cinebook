package com.cinebook.cinebook.repository;

import com.cinebook.cinebook.entity.Movie;
import com.cinebook.cinebook.enums.MovieStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
    List<Movie> findByStatus(MovieStatus status);
    List<Movie> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT DISTINCT m FROM Movie m JOIN m.genres g where g.id = :genreId")
    List<Movie> findByGenreId(Long genreId);
}
