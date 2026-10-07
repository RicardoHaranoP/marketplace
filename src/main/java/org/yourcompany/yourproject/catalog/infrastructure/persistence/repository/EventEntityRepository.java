package org.yourcompany.yourproject.catalog.infrastructure.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.yourcompany.yourproject.catalog.infrastructure.persistence.entity.Event;

@RepositoryRestResource
public interface EventEntityRepository extends JpaRepository<Event, UUID> {
}
