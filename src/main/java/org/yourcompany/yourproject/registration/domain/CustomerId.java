package org.yourcompany.yourproject.registration.domain;

import java.util.UUID;

import org.springframework.util.Assert;

public record CustomerId(UUID id) {
    public CustomerId {
        Assert.notNull(id, "CustomerId cannot be null");
    }

    public CustomerId() {
        this(UUID.randomUUID());
    }
}