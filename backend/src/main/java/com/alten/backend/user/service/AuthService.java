package com.alten.backend.user.service;

import com.alten.backend.config.JwtService;
import com.alten.backend.user.dto.AuthRequest;
import com.alten.backend.user.dto.AuthResponse;
import com.alten.backend.user.dto.RegisterRequest;
import com.alten.backend.user.entity.User;
import com.alten.backend.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent() || userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Username or email already exists");
        }

        User user = new User(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole() == null || request.getRole().isBlank() ? "USER" : request.getRole()
        );
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Bad credentials");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }

    public User getCurrentUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
