package com.cinebook.cinebook.controller;

import com.cinebook.cinebook.dto.genre.GenreRequest;
import com.cinebook.cinebook.dto.genre.GenreResponse;
import com.cinebook.cinebook.service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {
    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreResponse>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @PostMapping
    public ResponseEntity<List<GenreResponse>> createGenre(@Valid @RequestBody GenreRequest request) {
        return new ResponseEntity<>(genreService.createGenre(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}
