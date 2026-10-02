package com.cinebook.cinebook.dto.auth;
import com.cinebook.cinebook.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;      // access token lifetime in seconds — tells the client when to refresh
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private Role role;
}