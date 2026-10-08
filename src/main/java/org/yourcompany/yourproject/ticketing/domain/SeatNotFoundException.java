package org.yourcompany.yourproject.ticketing.domain;

public class SeatNotFoundException extends RuntimeException {
    public SeatNotFoundException(EventId eventId, SeatId seatId) {
        super("Seat " + seatId.id() + " not found in event " + eventId.id());
    }
}
