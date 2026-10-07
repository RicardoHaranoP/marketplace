package org.yourcompany.yourproject.catalog.application;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.yourcompany.yourproject.catalog.application.dto.EventOutput;
import org.yourcompany.yourproject.catalog.domain.Event;
import org.yourcompany.yourproject.catalog.domain.EventRepository;

@Service
public class BrowserShowCaseUseCase {
    private final EventRepository eventRepository;
    private final EventEnricher eventEnricher;

    public BrowserShowCaseUseCase(EventRepository eventRepository, EventEnricher eventEnricher) {
        this.eventRepository = eventRepository;
        this.eventEnricher = eventEnricher;
    }

    @Cacheable(value = "showcase", unless = "#result.isEmpty()")
    public List<EventOutput> execute() {
        var futures = eventRepository.findAll().stream()
                .map(eventEnricher::enrich)
                .toList();

        return futures.stream()
                .map(CompletableFuture::join)
                .map(EventOutput::from)
                .toList();
    }
}