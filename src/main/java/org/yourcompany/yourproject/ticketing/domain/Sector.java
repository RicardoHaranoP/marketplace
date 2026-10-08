package org.yourcompany.yourproject.ticketing.domain;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Getter;

@Getter
public class Sector {
    private UUID id;
    private SectorId correlationId;
    private BigDecimal price;

    public Sector(String correlationId, BigDecimal price) {
        this.id = UUID.randomUUID();
        this.correlationId = new SectorId(correlationId);
        this.price = price;
    }
}
