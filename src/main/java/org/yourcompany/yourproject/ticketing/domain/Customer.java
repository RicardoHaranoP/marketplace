package org.yourcompany.yourproject.ticketing.domain;

import java.util.UUID;

import lombok.Getter;
import org.yourcompany.yourproject.registration.domain.CustomerId;

@Getter 
public class Customer {
    private final UUID id;
    private final CustomerId correlationId;
    private final String name;

    public Customer(String correlationId, String name){
        this.id = UUID.randomUUID();
        this.correlationId = new CustomerId(UUID.fromString(correlationId));
        this.name = name;
    }
}
