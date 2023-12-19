package com.example.CourierTrackingSystem.exception;

public class CourierNotFoundException extends Throwable {
    public CourierNotFoundException(String courierNotFound) {
        super(courierNotFound);
    }
}
