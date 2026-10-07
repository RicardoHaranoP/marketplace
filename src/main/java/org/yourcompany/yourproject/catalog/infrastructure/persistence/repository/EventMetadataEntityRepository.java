package org.yourcompany.yourproject.catalog.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.yourcompany.yourproject.catalog.infrastructure.persistence.entity.EventMetadata;


@RepositoryRestResource
public interface EventMetadataEntityRepository extends MongoRepository<EventMetadata, String> {
    Optional<EventMetadata> findByEventId(UUID eventId);

}
