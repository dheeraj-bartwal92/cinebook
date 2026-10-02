package com.cinebook.cinebook.service;

import com.cinebook.cinebook.dto.theater.TheaterRequest;
import com.cinebook.cinebook.dto.theater.TheaterResponse;
import com.cinebook.cinebook.entity.Theater;
import com.cinebook.cinebook.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterService {
    private final TheaterRepository repository;

    public TheaterResponse createTheater(TheaterRequest request) {
        Theater theater = new Theater();
        theater.setName(request.getName());
        theater.setAddress(request.getAddress());
        theater.setCity(request.getCity());
        return mapToResponse(repository.save(theater));
    }

    public List<TheaterResponse> getAllTheaters() {
       return repository.findAll().stream().map(this::mapToResponse).toList();
    }

    public List<TheaterResponse> getTheatersByCity(String city) {
        return repository.findByCity(city).stream().map(this::mapToResponse).toList();
    }

    public TheaterResponse updateTheater(Long id, TheaterRequest request) {
        Theater theater = findOrThrow(id);
        theater.setName(request.getName());
        theater.setAddress(request.getAddress());
        theater.setCity(request.getCity());

        Theater updated = repository.save(theater);
        return mapToResponse(updated);
    }

    private Theater findOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theater not found: " + id));
    }

    public void deleteTheater(Long id) {
        Theater theater = findOrThrow(id);
        repository.delete(theater);
    }

    public TheaterResponse getTheaterById(Long id) {
        Theater theater = findOrThrow(id);
        return mapToResponse(theater);
    }

    private TheaterResponse mapToResponse(Theater theater) {
        return new TheaterResponse(
                theater.getId(),
                theater.getName(),
                theater.getAddress(),
                theater.getCity()
        );
    }
}
