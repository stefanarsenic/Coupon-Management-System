package org.example.gateway.config;

import lombok.RequiredArgsConstructor;
import org.example.gateway.filter.AuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthFilter authenticationFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("auth-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://auth-service"))
                .route("coupon-generation-service", r -> r.path("/api/coupons")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://coupon-generation-service"))
                .route("search-service", r -> r.path("/api/coupons/search/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://search-service"))
                .build();
    }
}
