package org.yourcompany.yourproject.catalog.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.yourcompany.yourproject.catalog.domain.EventId;
import org.yourcompany.yourproject.catalog.domain.EventMetadata;
import org.yourcompany.yourproject.catalog.domain.EventMetadataRepository;
import org.yourcompany.yourproject.catalog.domain.Seat;
import org.yourcompany.yourproject.catalog.domain.SeatId;
import org.yourcompany.yourproject.catalog.domain.Sector;
import org.yourcompany.yourproject.catalog.domain.SectorId;

@Repository
public class MongoEventMetadataRepository implements EventMetadataRepository {
    private final EventMetadataEntityRepository eventMetadataEntityRepository;

    public MongoEventMetadataRepository(EventMetadataEntityRepository eventMetadataEntityRepository) {
        this.eventMetadataEntityRepository = eventMetadataEntityRepository;
    }

    @Override
    public Optional<EventMetadata> findByEventId(EventId eventId) {
        return eventMetadataEntityRepository.findByEventId(eventId.id()).map(MongoEventMetadataRepository::mapper);
    }

    private static EventMetadata mapper(
            org.yourcompany.yourproject.catalog.infrastructure.persistence.entity.EventMetadata eventMetadata) {
        var sectors = eventMetadata.getSectors().stream()
                .map(MongoEventMetadataRepository::mapper)
                .collect(Collectors.toMap(
                        sector -> sector.getId().name(),
                        Function.identity()));

        var seats = eventMetadata.getSeats().stream()
                .map(MongoEventMetadataRepository::mapper)
                .collect(Collectors.groupingBy(
                        seat -> sectors.get(seat.getSectorId().name())));

        return new EventMetadata(
                eventMetadata.getEventDescription(),
                eventMetadata.getTechnicalRequirements(),
                seats);
    }

    private static Seat mapper(
            org.yourcompany.yourproject.catalog.infrastructure.persistence.entity.EventMetadata.Seat seat) {
        return new Seat(new SeatId(seat.getCode()), new SectorId(seat.getSectorName()));
    }

    private static Sector mapper(
            org.yourcompany.yourproject.catalog.infrastructure.persistence.entity.EventMetadata.Sector sector) {
        return new Sector(new SectorId(sector.getName()), sector.getPrice());
    }
}
