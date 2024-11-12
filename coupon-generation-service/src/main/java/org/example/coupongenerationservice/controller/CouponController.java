package org.example.coupongenerationservice.controller;

import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.example.coupongenerationservice.model.Coupon;
import org.example.coupongenerationservice.records.CouponRequest;
import org.example.coupongenerationservice.service.CouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/api/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateCoupons(@RequestBody CouponRequest request) {
        try {
            ArrayList<Coupon> coupons = couponService.generateCoupons(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(coupons);
        }
        catch (BadRequestException e) {
            HashMap<String, String> errorMessage = new HashMap<>();
            errorMessage.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
        }
    }
}
