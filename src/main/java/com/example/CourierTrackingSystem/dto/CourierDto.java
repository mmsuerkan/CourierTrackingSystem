package com.example.CourierTrackingSystem.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.CourierTrackingSystem.model.Courier}
 */
@Value
public class CourierDto implements Serializable {
    Long courierId ;
}