package org.yourcompany.yourproject.ticketing.domain;

import java.util.UUID;

import org.springframework.util.Assert;

public record EventId(UUID id) {
    public EventId {
        Assert.notNull(id, "id must not be null");
    }

    public EventId(String id) {
        this(UUID.fromString(id));
    }
    
}
