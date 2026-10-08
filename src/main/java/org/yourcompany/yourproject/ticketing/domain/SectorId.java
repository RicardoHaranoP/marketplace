package org.yourcompany.yourproject.ticketing.domain;

import org.springframework.util.Assert;

public record SectorId(String id) {
    public SectorId {
        Assert.notNull(id, "id must not be null");
    }
    
}
