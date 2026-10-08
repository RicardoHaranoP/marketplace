package org.yourcompany.yourproject.ticketing.domain;

public class SeatAlreadyReservedException extends RuntimeException {
    public SeatAlreadyReservedException() {
        super("seat already reserved");
    }
}
