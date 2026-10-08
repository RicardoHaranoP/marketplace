package org.yourcompany.yourproject.ticketing.application;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.yourcompany.yourproject.common.infrastructure.event.dto.EventUpdated;
import org.yourcompany.yourproject.ticketing.domain.Event;
import org.yourcompany.yourproject.ticketing.domain.EventRepository;
import org.yourcompany.yourproject.ticketing.domain.Seat;
import org.yourcompany.yourproject.ticketing.domain.Sector;

@Service
public class CreateEventUseCase {
    private final EventRepository eventRepository;

    public CreateEventUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void execute(EventUpdated event) {
        Map<Sector, List<Seat>> seats = event.sectors().stream()
            .collect(Collectors.toMap(
                sector -> new Sector(sector.id(), sector.price()),
                sector -> sector.seats().stream()
                    .map(seatDto -> new Seat(seatDto.number()))
                    .toList()
                    ));
    eventRepository.save(new Event(event.id(),seats));
                }
}

