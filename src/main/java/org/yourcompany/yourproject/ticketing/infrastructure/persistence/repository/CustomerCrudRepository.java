package org.yourcompany.yourproject.ticketing.infrastructure.persistence.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.yourcompany.yourproject.ticketing.infrastructure.persistence.entity.Customer;

@RepositoryRestResource(exported = false, path="_customer")
public interface CustomerCrudRepository extends CrudRepository<Customer, UUID>{
    
}
