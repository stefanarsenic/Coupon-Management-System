package org.example.searchservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.searchservice.CouponRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
public class CouponSearchController {

    private final CouponRepository couponRepository;

    @GetMapping("/search")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(couponRepository.findAll());
    }
}
