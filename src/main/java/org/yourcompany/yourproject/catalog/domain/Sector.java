package org.yourcompany.yourproject.catalog.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class Sector {
    private SectorId id;
    private BigDecimal price;

    public Sector(SectorId id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }
}
