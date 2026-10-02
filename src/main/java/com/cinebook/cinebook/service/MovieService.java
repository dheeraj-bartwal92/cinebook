package com.cinebook.cinebook.service;

import com.cinebook.cinebook.dto.genre.GenreResponse;
import com.cinebook.cinebook.dto.movie.MovieRequest;
import com.cinebook.cinebook.dto.movie.MovieResponse;
import com.cinebook.cinebook.dto.movie.MovieStatusUpdateRequest;
import com.cinebook.cinebook.entity.Genre;
import com.cinebook.cinebook.entity.Movie;
import com.cinebook.cinebook.enums.MovieStatus;
import com.cinebook.cinebook.repository.GenreRepository;
import com.cinebook.cinebook.repository.MovieRepository;
import com.cinebook.cinebook.repository.MovieSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public MovieResponse createMovie(MovieRequest request) {
        Movie movie = new Movie();
        applyRequest(request,movie);
        movie.setStatus(MovieStatus.UPCOMING);
        return mapToResponse(movieRepository.save(movie));
    }

    public MovieResponse getMovieById(Long movieId) {
        return mapToResponse(findOrThrow(movieId));
    }

    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public List<MovieResponse> searchMovies(String title, MovieStatus status, Long genreId) {

        Specification<Movie> spec = Specification.where(null);

        if (title != null && !title.isBlank()) {
            spec = spec.and(MovieSpecifications.hasTitleContaining(title));
        }
        if (status != null) {
            spec = spec.and(MovieSpecifications.hasStatus(status));
        }
        if (genreId != null) {
            spec = spec.and(MovieSpecifications.hasGenreId(genreId));
        }

        return movieRepository.findAll(spec).stream()
                .map(this::mapToResponse)
                .toList();
    }

    public MovieResponse updateMovie(Long id, MovieRequest request) {
        Movie movie = findOrThrow(id);
        applyRequest(request, movie);
        return mapToResponse(movieRepository.save(movie));
    }

    private Movie findOrThrow(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found: " + id));
    }

    public MovieResponse updateStatus(Long id, MovieStatusUpdateRequest request) {
        Movie movie = findOrThrow(id);
        movie.setStatus(request.getStatus());

        return mapToResponse(movieRepository.save(movie));
    }

    private void applyRequest(MovieRequest request, Movie movie) {

        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setDurationMinutes(request.getDurationMinutes());
        movie.setReleaseDate(request.getReleaseDate());
        movie.setRating(request.getRating());
        movie.setPosterUrl(request.getPosterUrl());

        Set<Genre> genres = request.getGenreIds().stream().map(genreId -> genreRepository.findById(genreId).orElseThrow(()-> new RuntimeException("Genre not found: " + genreId)))
                .collect(Collectors.toSet());
        movie.setGenres(genres);
    }

    private MovieResponse mapToResponse(Movie movie) {
        Set<GenreResponse> genreResponses = movie.getGenres().stream()
                .map(g -> new GenreResponse(g.getId(), g.getName()))
                .collect(Collectors.toSet());

        return new MovieResponse(
                movie.getId(), movie.getTitle(), movie.getDescription(),
                movie.getDurationMinutes(), movie.getReleaseDate(), movie.getRating(),
                movie.getPosterUrl(), movie.getStatus(), genreResponses
        );
    }
}
