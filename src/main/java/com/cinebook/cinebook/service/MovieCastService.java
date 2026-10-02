package com.cinebook.cinebook.service;

import com.cinebook.cinebook.dto.moviecast.MovieCastRequest;
import com.cinebook.cinebook.dto.moviecast.MovieCastResponse;
import com.cinebook.cinebook.dto.person.PersonResponse;
import com.cinebook.cinebook.entity.Movie;
import com.cinebook.cinebook.entity.MovieCast;
import com.cinebook.cinebook.entity.Person;
import com.cinebook.cinebook.repository.MovieCastRepository;
import com.cinebook.cinebook.repository.MovieRepository;
import com.cinebook.cinebook.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieCastService {

    private final MovieCastRepository movieCastRepository;
    private final MovieRepository movieRepository;
    private final PersonRepository personRepository;

    public MovieCastResponse addCastMember(Long movieId, MovieCastRequest request) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found: " + movieId));

        Person person = personRepository.findById(request.getPersonId())
                .orElseThrow(() -> new RuntimeException("Person not found: " + request.getPersonId()));

        if (movieCastRepository.existsByMovieIdAndPersonIdAndRole(movieId, request.getPersonId(), request.getRole())) {
            throw new RuntimeException(
                    "This person is already credited as " + request.getRole() + " on this movie");
        }

        MovieCast cast = new MovieCast();
        cast.setMovie(movie);
        cast.setPerson(person);
        cast.setRole(request.getRole());
        cast.setCharacterName(request.getCharacterName());

        return mapToResponse(movieCastRepository.save(cast));
    }

    public List<MovieCastResponse> getCastByMovie(Long movieId) {
        return movieCastRepository.findByMovieId(movieId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<MovieCastResponse> getFilmographyByPerson(Long personId) {
        return movieCastRepository.findByPersonId(personId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    public void removeCastMember(Long id) {
        MovieCast cast = movieCastRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cast entry not found: " + id));
        movieCastRepository.delete(cast);
    }

    private MovieCastResponse mapToResponse(MovieCast cast) {
        PersonResponse personResponse = new PersonResponse(
                cast.getPerson().getId(),
                cast.getPerson().getName(),
                cast.getPerson().getPhotoUrl(),
                cast.getPerson().getBio()
        );

        return new MovieCastResponse(
                cast.getId(), personResponse, cast.getRole(), cast.getCharacterName()
        );
    }
}