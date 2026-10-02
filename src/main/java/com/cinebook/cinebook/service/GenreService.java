package com.cinebook.cinebook.service;

import com.cinebook.cinebook.dto.genre.GenreRequest;
import com.cinebook.cinebook.dto.genre.GenreResponse;
import com.cinebook.cinebook.entity.Genre;
import com.cinebook.cinebook.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public List<GenreResponse> createGenre(GenreRequest request) {

        List<Genre> genres = request.getNames().stream()
                .filter(name -> !genreRepository.existsByName(name))
                .map(name -> {
                    Genre genre = new Genre();
                    genre.setName(name);
                    return genre;
                }).toList();

        return genreRepository.saveAll(genres).stream().map(this::mapToResponse).toList();
    }

    public List<GenreResponse> getAllGenres() {
        return genreRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public void deleteGenre(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found: " + id));
        genreRepository.delete(genre);
    }

    private GenreResponse mapToResponse(Genre genre) {
        return new GenreResponse(genre.getId(), genre.getName());
    }
}
