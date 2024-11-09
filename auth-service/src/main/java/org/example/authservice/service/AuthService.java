package org.example.authservice.service;

import lombok.RequiredArgsConstructor;
import org.example.authservice.model.User;
import org.example.authservice.records.LoginRequest;
import org.example.authservice.records.RegisterRequest;
import org.example.authservice.repository.RoleRepository;
import org.example.authservice.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(RegisterRequest request){
        User newUser = new User(
                0L,
                request.username(),
                passwordEncoder.encode(request.password()),
                true, true,
                Arrays.asList(roleRepository.findByName("ROLE_USER"))
        );

        return userRepository.save(newUser);
    }

    public User authenticate(LoginRequest request){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.username(),
                    request.password()
            ));

            User user = userRepository.findByUsername(request.username());

            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }

            return user;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
