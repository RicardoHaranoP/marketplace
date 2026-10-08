package org.yourcompany.yourproject.ticketing.infrastructure.persistence.entity;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash (value = "seat_locks", timeToLive = 30)
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class SeatLock {
    @Id
    private String id;

    @Indexed 
    private String customerId;

    private Instant createdAt;
}