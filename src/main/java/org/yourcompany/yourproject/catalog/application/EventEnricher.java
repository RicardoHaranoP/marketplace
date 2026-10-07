package org.yourcompany.yourproject.catalog.application;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.yourcompany.yourproject.catalog.domain.Event;
import org.yourcompany.yourproject.catalog.domain.EventMetadataRepository;

@Service
public class EventEnricher {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventEnricher.class);
    private final EventMetadataRepository eventMetadataRepository;

    public EventEnricher(EventMetadataRepository eventMetadataRepository) {
        this.eventMetadataRepository = eventMetadataRepository;
    }

    @Async 
    public  CompletableFuture<Event> enrich(Event event) {
        LOGGER.info("Enriching event: {}", event);

        var metadata = eventMetadataRepository.findByEventId(event.getId());
        event.setMetadata(metadata);

        return CompletableFuture.completedFuture(event);
    }
}
