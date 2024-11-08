package org.example.authservice.records;

public record LoginResponse(
        String token,
        long expirationTime
) {
}
