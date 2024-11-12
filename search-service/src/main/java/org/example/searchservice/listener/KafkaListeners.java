package org.example.searchservice.listener;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.searchservice.CouponRepository;
import org.example.searchservice.model.Coupon;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListeners {

    private final CouponRepository couponRepository;
    @KafkaListener(topics = "coupons", groupId = "groupId")
    void listener(ConsumerRecord<String, Coupon> data) {
        try {
            couponRepository.save(data.value());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
