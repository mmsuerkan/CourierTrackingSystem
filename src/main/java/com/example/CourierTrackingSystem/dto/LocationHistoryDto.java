package com.example.CourierTrackingSystem.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.CourierTrackingSystem.model.LocationHistory}
 */
@Value
public class LocationHistoryDto implements Serializable {
    Long courierId;
    double latitude;
    double longitude;
    LocalDateTime timestamp;
}