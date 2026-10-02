package com.cinebook.cinebook.service;

import com.cinebook.cinebook.entity.User;
import com.cinebook.cinebook.repository.UserRepository;
import com.cinebook.cinebook.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        System.out.println("LOGIN VALUE = " + username);

        User user = repository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        System.out.println("USER FOUND = " + user.getUsername());

        UserPrincipal principal = new UserPrincipal(user);

        System.out.println(
                "PRINCIPAL TYPE = " + principal.getClass().getName()
        );

        return principal;
    }
}

