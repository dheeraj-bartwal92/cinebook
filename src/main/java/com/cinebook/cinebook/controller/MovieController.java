package com.cinebook.cinebook.controller;

import com.cinebook.cinebook.dto.movie.MovieRequest;
import com.cinebook.cinebook.dto.movie.MovieResponse;
import com.cinebook.cinebook.dto.movie.MovieStatusUpdateRequest;
import com.cinebook.cinebook.enums.MovieStatus;
import com.cinebook.cinebook.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getMovies(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) MovieStatus status,
            @RequestParam(required = false) Long genreId) {

        return ResponseEntity.ok(movieService.searchMovies(title, status, genreId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovie(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @PostMapping
    public ResponseEntity<MovieResponse> createMovie(@Valid @RequestBody MovieRequest request) {
        return new ResponseEntity<>(movieService.createMovie(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(
            @PathVariable Long id, @Valid @RequestBody MovieRequest request) {
        return ResponseEntity.ok(movieService.updateMovie(id, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<MovieResponse> updateStatus(
            @PathVariable Long id, @Valid @RequestBody MovieStatusUpdateRequest request) {
        return ResponseEntity.ok(movieService.updateStatus(id, request));
    }
}