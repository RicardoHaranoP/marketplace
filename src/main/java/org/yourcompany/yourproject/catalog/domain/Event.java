package org.yourcompany.yourproject.catalog.domain;

import java.time.Instant;
import java.util.Optional;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class Event {
    private EventId id;
    private String title;
    private Instant date;
    private Optional<EventMetadata> metadata;

    public Event(EventId id, String title, Instant date, Optional<EventMetadata> metadata) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.metadata = metadata;
    }
}