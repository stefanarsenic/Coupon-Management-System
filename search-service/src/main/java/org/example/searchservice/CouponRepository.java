package org.example.searchservice;

import lombok.RequiredArgsConstructor;
import org.example.searchservice.model.Coupon;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends CrudRepository<Coupon, String> {
}
