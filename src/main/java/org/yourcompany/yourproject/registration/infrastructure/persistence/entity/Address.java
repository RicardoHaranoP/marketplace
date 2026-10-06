package org.yourcompany.yourproject.registration.infrastructure.persistence.entity;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity 
@Data 
@RequiredArgsConstructor 
public class Address {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    private String street, postalCode, city, state;

    @CreationTimestamp
    @Column (updatable = false, nullable = false)
    private Instant createdAt;

    public String toString(){
        return String.format("%s, %s %s, %s", street, postalCode, city, state);
    }
}
