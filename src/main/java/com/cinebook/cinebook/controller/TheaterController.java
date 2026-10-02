package com.cinebook.cinebook.controller;

import com.cinebook.cinebook.dto.theater.TheaterRequest;
import com.cinebook.cinebook.dto.theater.TheaterResponse;
import com.cinebook.cinebook.service.TheaterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/theaters")
@RequiredArgsConstructor
public class TheaterController {
    private final TheaterService service;

    @PostMapping("/create")
    public ResponseEntity<TheaterResponse> create(@Valid @RequestBody TheaterRequest request) {
        TheaterResponse response = service.createTheater(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TheaterResponse> get(@PathVariable Long id) {
        TheaterResponse theater = service.getTheaterById(id);
        return ResponseEntity.ok(theater);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TheaterResponse> update(@PathVariable Long id, @Valid @RequestBody TheaterRequest request) {
       TheaterResponse updateTheater = service.updateTheater(id, request);
       return ResponseEntity.ok(updateTheater);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteTheater(id);
        return ResponseEntity.noContent().build();
    }

}
