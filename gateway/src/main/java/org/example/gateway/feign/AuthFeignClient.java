package org.example.gateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service", url = "http://localhost:8050/auth")
public interface AuthFeignClient {

    @GetMapping("/validateToken")
    Boolean validateToken(@RequestHeader("Authorization") String token);
}
