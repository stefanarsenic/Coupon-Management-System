package org.example.searchservice;

import lombok.RequiredArgsConstructor;
import org.example.searchservice.model.Coupon;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CouponRepository {

    public static final String HASH_KEY = "Coupon";
    private final RedisTemplate redisTemplate;

    public Coupon save(Coupon coupon) {
        redisTemplate.opsForHash().put(HASH_KEY, coupon.getId(), coupon);
        return coupon;
    }

    public List<Coupon> findAll() {
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public Coupon findByCategory(String category) {
        return (Coupon) redisTemplate.opsForHash().get(HASH_KEY, category);
    }
}
