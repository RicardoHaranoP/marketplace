package org.yourcompany.yourproject.ticketing.application;

import org.springframework.stereotype.Service;
import org.yourcompany.yourproject.common.infrastructure.event.dto.CustomerCreated;
import org.yourcompany.yourproject.ticketing.domain.Customer;
import org.yourcompany.yourproject.ticketing.domain.CustomerRepository;

@Service
public class CreateCustomerUseCase {
    private final CustomerRepository customerRepository;

    public CreateCustomerUseCase(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public void execute(CustomerCreated event) {
        var customer = new Customer(event.id(), event.name());
        customerRepository.save(customer);
    }
}
