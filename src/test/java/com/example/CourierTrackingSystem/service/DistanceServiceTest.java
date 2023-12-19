package com.example.CourierTrackingSystem.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceServiceTest {

    DistanceService distanceService = new DistanceService();

    @Test
    void calculateDistance() {
        //courier1
        double lat1 = 40.992;
        double lon1 = 29.124;
        //Ataşehir MMM Migros
        double lat2 = 40.9923307;
        double lon2 = 29.1244229;

        double distance = distanceService.calculateDistance(lat1, lon1, lat2, lon2);

        assertEquals(50, customRound(distance,1), 0.1);
    }

    public static double customRound(double number, int roundingDigit) {
        if (roundingDigit < 0) {
            throw new IllegalArgumentException("Yuvarlama basamağı negatif olamaz.");
        }

        double scale = Math.pow(10, roundingDigit);
        return Math.floor(number / 1000) * 1000 + Math.round((number % 1000) / scale) * scale;
    }
}