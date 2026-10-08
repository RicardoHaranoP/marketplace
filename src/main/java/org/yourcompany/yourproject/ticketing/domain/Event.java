package org.yourcompany.yourproject.ticketing.domain;

import java.util.List;
import java.util.Map;
import java.util.UUID;



import lombok.Getter;

@Getter
public class Event {
    private UUID id;
    private EventId correlationId;
    private Map<Sector, List<Seat>> seats;

    public Event(String correlationId, Map<Sector, List<Seat>> seats) {
        this.id = UUID.randomUUID();
        this.correlationId = new EventId(correlationId);
        this.seats = seats;
    }
}
