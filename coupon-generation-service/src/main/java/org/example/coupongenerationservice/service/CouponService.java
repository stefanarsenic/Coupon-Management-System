package org.example.coupongenerationservice.service;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.example.coupongenerationservice.model.Coupon;
import org.example.coupongenerationservice.records.CouponRequest;
import org.example.coupongenerationservice.repository.CouponRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    @Transactional
    public ArrayList<Coupon> generateCoupons(CouponRequest request) {
        try {
            ArrayList<Coupon> coupons = new ArrayList<>();
            for(int i = 0; i <= request.quantity(); i++) {
                Coupon c = new Coupon();
                c.setDescription(request.description());
                c.setCategory(request.category());
                c.setType(request.type());

                coupons.add(couponRepository.save(c));
            }

            return coupons;
        }
        catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
