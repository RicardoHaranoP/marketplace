package org.yourcompany.yourproject.registration.infrastructure.persistence.entity.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import org.yourcompany.yourproject.registration.infrastructure.persistence.entity.Customer;


@Projection (name = "except", types = Customer.class)
public interface CustomerExcept {
    
    String getFirstName();
    String getLastName();

    @Value("#{target.address?.toString()}")
    String getAddress();
}
