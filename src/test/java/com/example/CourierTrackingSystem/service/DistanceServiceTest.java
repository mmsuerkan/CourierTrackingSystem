package com.example.CourierTrackingSystem.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceServiceTest {

    @Test
    void calculateDistance() {
        double lat1 = 40.9923307;
        double lon1 = 29.1244229;

        double lat2 = 40.986106;
        double lon2 = 29.1161293;

        double distance = DistanceService.calculateDistance(lat1, lon1, lat2, lon2);

        assertEquals(1000, customRound(distance, 3), 0.1);
    }

    public static double customRound(double number, int roundingDigit) {
        if (roundingDigit < 0) {
            throw new IllegalArgumentException("Yuvarlama basamağı negatif olamaz.");
        }

        double scale = Math.pow(10, roundingDigit);
        return Math.floor(number / 1000) * 1000 + Math.round((number % 1000) / scale) * scale;
    }
}