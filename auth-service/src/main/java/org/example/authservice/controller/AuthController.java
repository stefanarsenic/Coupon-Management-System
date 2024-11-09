package org.example.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.authservice.model.User;
import org.example.authservice.records.LoginRequest;
import org.example.authservice.records.LoginResponse;
import org.example.authservice.records.RegisterRequest;
import org.example.authservice.records.RegisterResponse;
import org.example.authservice.service.AuthService;
import org.example.authservice.service.JwtService;
import org.example.authservice.service.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        User registeredUser = authService.register(request);

        RegisterResponse registerResponse = new RegisterResponse(registeredUser.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            User authenticatedUser = authService.authenticate(request);

            UserDetails u = userDetailsService.loadUserByUsername(authenticatedUser.getUsername());
            String token = jwtService.generateToken(u);
            long expirationTime = jwtService.getExpirationTime();

            LoginResponse loginResponse = new LoginResponse(token, expirationTime);

            return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
        }
        catch (BadCredentialsException e) {
            HashMap<String, String> errorMessage = new HashMap<>();
            errorMessage.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
}
