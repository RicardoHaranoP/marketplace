package org.yourcompany.yourproject.catalog.infrastructure.persistence.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Document 
@RequiredArgsConstructor 
public class EventMetadata {
    @Id 
    private String id;

    @NotNull
    private UUID eventId;

    private String eventDescription;

    private Map<String, Object> technicalRequirements;

    private List<Sector> sectors;

    private List<Seat> seats;

    @CreatedDate
    private Instant CreatedAt;

    @LastModifiedDate 
    private Instant updatedAt;

    @Data
    @NoArgsConstructor 
    @AllArgsConstructor 
    public static class Sector {
        private String name;
        private BigDecimal price;
    }

    @Data
    @NoArgsConstructor 
    @AllArgsConstructor 
    public static class Seat {
        private String code;
        private String sectorName;
    }
}
