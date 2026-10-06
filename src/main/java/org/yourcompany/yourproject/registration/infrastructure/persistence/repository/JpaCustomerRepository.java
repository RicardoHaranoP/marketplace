package org.yourcompany.yourproject.registration.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Repository;
import org.yourcompany.yourproject.registration.domain.Customer;
import org.yourcompany.yourproject.registration.domain.CustomerId;
import org.yourcompany.yourproject.registration.domain.CustomerRepository;


@Repository
public class JpaCustomerRepository implements CustomerRepository {

    private final CustomerEntityRepository customerEntityRepository;

    public JpaCustomerRepository(CustomerEntityRepository customerEntityRepository) {
        this.customerEntityRepository = customerEntityRepository;
    }

    @Override 
    public Customer save(Customer customer) {
        var entity = mapper(customer);
        customerEntityRepository.save(entity);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        var iterable = customerEntityRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
            .map(JpaCustomerRepository::mapper).toList();
    }

    private static org.yourcompany.yourproject.registration.infrastructure.persistence.entity.Customer mapper(Customer customer) {
        var entity = new org.yourcompany.yourproject.registration.infrastructure.persistence.entity.Customer();
        entity.setId(customer.getId().id());
        entity.setFirstName(customer.getName());
        entity.setEmail(customer.getEmail());
        return entity;
    }

    private static Customer mapper(org.yourcompany.yourproject.registration.infrastructure.persistence.entity.Customer entity) {
        String fullName = Optional.ofNullable(entity.getLastName())
            .map(lastName -> entity.getFirstName() + " " + lastName)
            .orElseGet(entity::getFirstName);

            return new Customer(new CustomerId(entity.getId()), fullName, entity.getEmail());
    }
}
