package org.example.authservice.records;

public record RegisterRequest(
        String username,
        String password
) {
}
