package org.yourcompany.yourproject.ticketing.application;

import org.springframework.stereotype.Service;
import org.yourcompany.yourproject.ticketing.domain.CustomerId;
import org.yourcompany.yourproject.ticketing.domain.EventId;
import org.yourcompany.yourproject.ticketing.domain.EventRepository;
import org.yourcompany.yourproject.ticketing.domain.SeatAlreadyReservedException;
import org.yourcompany.yourproject.ticketing.domain.SeatId;
import org.yourcompany.yourproject.ticketing.domain.SeatNotFoundException;

@Service 
public class SelectSeatUseCase {
    private final EventRepository eventRepository;

    public SelectSeatUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void execute(EventId eventId, SeatId seatId, CustomerId customerId) {
        if (!eventRepository.existSeat(eventId, seatId)) {
            throw new SeatNotFoundException(eventId, seatId);
        }

        boolean lock = eventRepository.tryLockSeat(eventId, seatId, customerId);

        if (!lock) {
            throw new SeatAlreadyReservedException();
        }
    }
}
