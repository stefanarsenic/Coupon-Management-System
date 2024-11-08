package org.example.authservice.records;

public record LoginRequest(
        String username,
        String password
) {
}
