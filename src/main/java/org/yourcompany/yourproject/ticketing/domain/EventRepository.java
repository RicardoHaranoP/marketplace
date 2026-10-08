package org.yourcompany.yourproject.ticketing.domain;

public interface EventRepository {
    void save(Event event);
    boolean existSeat(EventId eventId, SeatId seatId);

    boolean tryLockSeat(EventId eventId, SeatId seatId, CustomerId customerId);
}
