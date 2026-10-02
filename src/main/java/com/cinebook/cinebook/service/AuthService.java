package com.cinebook.cinebook.service;

import com.cinebook.cinebook.dto.auth.AuthResponse;
import com.cinebook.cinebook.dto.auth.LoginRequest;
import com.cinebook.cinebook.dto.auth.SignupRequest;
import com.cinebook.cinebook.entity.User;
import com.cinebook.cinebook.enums.Role;
import com.cinebook.cinebook.repository.UserRepository;
import com.cinebook.cinebook.security.RefreshTokenGenerator;
import com.cinebook.cinebook.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.cinebook.cinebook.security.UserPrincipal;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenGenerator refreshTokenGenerator;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthResponse signup(SignupRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Duplicate user");
        }

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Duplicate user");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setRole(Role.CUSTOMER);
        user.setEnabled(true);
        User savedUser = userRepository.save(user);

        String token = "";

        return new AuthResponse(
                token,
                "",
                0L,
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getFullName(),
                savedUser.getRole()
        );

    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsernameOrEmail(), request.getPassword()));
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        User user = principal.getUser();
        String accessToken = jwtUtil.generateToken(user);
        String refreshToken = refreshTokenGenerator.generateAndSave(user);
        return new AuthResponse(
                accessToken,
                refreshToken,
                jwtUtil.getAccessTokenExpirationSeconds(),
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFullName(),
                user.getRole()
        );
    }
}
