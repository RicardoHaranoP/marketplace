package org.yourcompany.yourproject.ticketing.domain;

import java.util.UUID;

import org.springframework.util.Assert;

public record CustomerId(UUID id) {
    public CustomerId {
        Assert.notNull(id, "id must not be null");
    }

    public CustomerId(String id) {
        this(UUID.fromString(id));
    }
}
