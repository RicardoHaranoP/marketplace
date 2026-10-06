package org.yourcompany.yourproject.registration.infrastructure.persistence.entity;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity 
@Data
@RequiredArgsConstructor 
public class Customer {
    @Id 
    private UUID id;

    @NotBlank 
    @Column(nullable = false)
    private String firstName;
    private String lastName;

    @NotBlank 
    @Email 
    @Column (nullable = false, unique = true)
    private String email;

    @CreationTimestamp 
    @Column (nullable = false, updatable = false)
    private Instant createdAt;

    @OneToOne (cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    @PrePersist 
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }
}
