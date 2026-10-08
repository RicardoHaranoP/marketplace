package org.yourcompany.yourproject.ticketing.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.yourcompany.yourproject.ticketing.infrastructure.persistence.entity.SeatLock;

@RepositoryRestResource (exported = false)
public interface RedisSeatLockRepository extends CrudRepository<SeatLock, String>{
    Optional<SeatLock> findByCustomerId(String customerId);
}
