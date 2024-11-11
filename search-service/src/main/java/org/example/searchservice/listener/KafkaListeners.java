package org.example.searchservice.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "coupons", groupId = "groupId")
    void listener(String data) {
        System.out.println("Data: " + data);
    }
}
