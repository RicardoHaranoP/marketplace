package org.yourcompany.yourproject.ticketing.infrastructure.http.request;

import org.yourcompany.yourproject.ticketing.domain.SeatId;

public record SeatSelectionRequest(String id) {
    public SeatId toInput() {
        return new SeatId(id);
    }
}
