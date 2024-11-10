package org.example.coupongenerationservice.records;

import java.util.Date;

public record CouponRequest(
        int quantity,
        String category,
        String description,
        String type
) {}
