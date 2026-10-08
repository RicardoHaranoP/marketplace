package org.yourcompany.yourproject.ticketing.infrastructure.persistence.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.yourcompany.yourproject.ticketing.domain.CustomerId;
import org.yourcompany.yourproject.ticketing.domain.Event;
import org.yourcompany.yourproject.ticketing.domain.EventId;
import org.yourcompany.yourproject.ticketing.domain.EventRepository;
import org.yourcompany.yourproject.ticketing.domain.Seat;
import org.yourcompany.yourproject.ticketing.domain.SeatId;
import org.yourcompany.yourproject.ticketing.domain.Sector;
import org.yourcompany.yourproject.ticketing.infrastructure.persistence.entity.SeatLock;

@Repository
public class WorkOfUnitEventRepository implements EventRepository {
    private final EventCrudRepository eventCrudRepository;
    private final RedisSeatLockRepository redisSeatLockRepository;

    public WorkOfUnitEventRepository(EventCrudRepository eventCrudRepository, RedisSeatLockRepository redisSeatLockRepository){
        this.eventCrudRepository = eventCrudRepository;
        this.redisSeatLockRepository = redisSeatLockRepository;
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

    @Override 
    public boolean existSeat(EventId eventId, SeatId seatId) {
        return eventCrudRepository.existsByCorrelationIdAndSectorsSeatsCorrelationId(eventId.id(), seatId.id());
    }

    @Override
    public boolean tryLockSeat(EventId eventId, SeatId seatId, CustomerId customerId) {
        String lockId = eventId.id().toString() + ":" + seatId.id();

        if (redisSeatLockRepository.existsById(lockId)) {
            return false;
        }

        var lock = new SeatLock(lockId, customerId.id().toString(), Instant.now());
        redisSeatLockRepository.save(lock);
        return true;
    }
}
