package org.yourcompany.yourproject.catalog.infrastructure.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import org.yourcompany.yourproject.catalog.infrastructure.persistence.entity.Event;

public class EventListener {
    public static final Logger logger = LoggerFactory.getLogger(EventListener.class);

    @PostPersist
    public void onEventCreated(Event event) {
        logger.info("Event created via @PostPersist {}", event);
    }

    @PostUpdate
    public void onEventUpdated(Event event) {
        logger.info("Event updated via @PostUpdate {}", event);
    }

    @PostRemove
    public void onEventDeleted(Event event){
        logger.info("Event deleted via @PostRemove {}", event);
    }
}
