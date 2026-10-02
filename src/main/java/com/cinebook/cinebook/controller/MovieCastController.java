package com.cinebook.cinebook.controller;

import com.cinebook.cinebook.dto.moviecast.MovieCastRequest;
import com.cinebook.cinebook.dto.moviecast.MovieCastResponse;
import com.cinebook.cinebook.service.MovieCastService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieCastController {

    private final MovieCastService movieCastService;

    @GetMapping("/api/movies/{movieId}/cast")
    public ResponseEntity<List<MovieCastResponse>> getCastByMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(movieCastService.getCastByMovie(movieId));
    }

    @GetMapping("/api/people/{personId}/filmography")
    public ResponseEntity<List<MovieCastResponse>> getFilmography(@PathVariable Long personId) {
        return ResponseEntity.ok(movieCastService.getFilmographyByPerson(personId));
    }

    @PostMapping("/api/movies/{movieId}/cast")
    public ResponseEntity<MovieCastResponse> addCastMember(
            @PathVariable Long movieId, @Valid @RequestBody MovieCastRequest request) {
        return new ResponseEntity<>(movieCastService.addCastMember(movieId, request), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/movie-cast/{id}")
    public ResponseEntity<Void> removeCastMember(@PathVariable Long id) {
        movieCastService.removeCastMember(id);
        return ResponseEntity.noContent().build();
    }
}