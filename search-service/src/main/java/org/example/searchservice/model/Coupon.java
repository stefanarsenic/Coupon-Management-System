package org.example.searchservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Coupon")
public class Coupon implements Serializable {

    @Id
    private String id;
    private String category;
    private String description;
    private Date date;
    private String type;
}
