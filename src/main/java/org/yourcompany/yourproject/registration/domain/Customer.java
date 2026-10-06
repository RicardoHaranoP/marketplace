package org.yourcompany.yourproject.registration.domain;
import org.springframework.util.Assert;

import lombok.Getter;

@Getter 
public class Customer {
    private CustomerId id;
    private String name;
    private String email;

    public Customer(CustomerId id, String name, String email) {
        Assert.notNull(name, "Customer name cannot be null");
        Assert.notNull(email, "Customer email cannot be null");
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Customer(String name, String email) {
        this(new CustomerId(), name, email);
    }
}