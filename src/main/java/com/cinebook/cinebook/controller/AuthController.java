package com.cinebook.cinebook.controller;

import com.cinebook.cinebook.dto.auth.AuthResponse;
import com.cinebook.cinebook.dto.auth.LoginRequest;
import com.cinebook.cinebook.dto.auth.SignupRequest;
import com.cinebook.cinebook.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
 private final AuthService authService;

 @PostMapping("/login")
 public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
    AuthResponse authResponse = authService.login(request);
    return ResponseEntity.ok().body(authResponse);
 }

 @PostMapping("/signup")
 public ResponseEntity<AuthResponse> signup(@Valid @RequestBody SignupRequest request) {
     AuthResponse authResponse = authService.signup(request);
     return ResponseEntity.ok(authResponse);
 }


    @Controller

    public static class TheaterController {
    }
}
