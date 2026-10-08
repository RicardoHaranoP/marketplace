package org.yourcompany.yourproject.ticketing.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;
import org.yourcompany.yourproject.ticketing.domain.Event;
import org.yourcompany.yourproject.ticketing.domain.EventRepository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.yourcompany.yourproject.ticketing.domain.Event;
import org.yourcompany.yourproject.ticketing.domain.EventRepository;
import org.yourcompany.yourproject.ticketing.domain.Seat;
import org.yourcompany.yourproject.ticketing.domain.Sector;

@Repository
public class PostgresEventRepository implements EventRepository {
    private final EventCrudRepository eventCrudRepository;

    public PostgresEventRepository(EventCrudRepository eventCrudRepository){
        this.eventCrudRepository = eventCrudRepository;
    }

    @Override
    public void save(Event event) {
        var sectors = event.getSeats().entrySet().stream()
            .map(entry -> {
                Sector domainSector = entry.getKey();
                List<Seat> domainSeats = entry.getValue();

                var seats = domainSeats.stream()
                    .map(seat -> new org.yourcompany.yourproject.ticketing.infrastructure.persistence.entity.Seat(
                        seat.getId(),
                        seat.getCorrelationId().id()))
                    .toList();

                return new org.yourcompany.yourproject.ticketing.infrastructure.persistence.entity.Sector(
                    domainSector.getId(),
                    domainSector.getCorrelationId().id(),
                    domainSector.getPrice(),
                    seats);
            }).toList();
        var entity = new org.yourcompany.yourproject.ticketing.infrastructure.persistence.entity.Event(
            event.getId(),
            event.getCorrelationId().id(),
            sectors);

        eventCrudRepository.save(entity);
    }
}
