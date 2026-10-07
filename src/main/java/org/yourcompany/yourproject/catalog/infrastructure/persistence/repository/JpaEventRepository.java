package org.yourcompany.yourproject.catalog.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Repository;
import org.yourcompany.yourproject.catalog.domain.Event;
import org.yourcompany.yourproject.catalog.domain.EventId;
import org.yourcompany.yourproject.catalog.domain.EventRepository;

@Repository
public class JpaEventRepository implements EventRepository {

    private final EventEntityRepository eventEntityRepository;

    public JpaEventRepository(EventEntityRepository eventEntityRepository) {
        this.eventEntityRepository = eventEntityRepository;
    }

    @Override 
    public List<Event> findAll() {
        var iterable = eventEntityRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
        .map(JpaEventRepository::mapper).toList();
        
    }

    private static Event mapper(org.yourcompany.yourproject.catalog.infrastructure.persistence.entity.Event event) {
        return new Event(new EventId(event.getId()), event.getTitle(), event.getDate(), Optional.empty());
    }
}
