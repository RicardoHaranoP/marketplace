package org.yourcompany.yourproject.catalog.domain;

import java.util.List;

public interface EventRepository {
    List<Event> findAll();
}
