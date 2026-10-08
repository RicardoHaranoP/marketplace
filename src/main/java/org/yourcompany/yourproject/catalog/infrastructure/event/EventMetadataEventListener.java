package org.yourcompany.yourproject.catalog.infrastructure.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.yourcompany.yourproject.catalog.infrastructure.persistence.entity.EventMetadata;
import org.yourcompany.yourproject.common.infrastructure.event.dto.EventUpdated;

public class EventMetadataEventListener extends AbstractMongoEventListener<EventMetadata> {
    private static final Logger logger = LoggerFactory.getLogger(EventMetadataEventListener.class);
 
    private final ApplicationEventPublisher publisher;

    public EventMetadataEventListener(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void onAfterSave(AfterSaveEvent<EventMetadata> event) {
        logger.info("Event metadata save via onAfterSave {}", event.getDocument());
        this.publisher.publishEvent(EventUpdated.from(event.getSource()));
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<EventMetadata> event) {
        logger.info("Event metadata delete via onAfterDelete {}", event.getDocument());
    }
}
