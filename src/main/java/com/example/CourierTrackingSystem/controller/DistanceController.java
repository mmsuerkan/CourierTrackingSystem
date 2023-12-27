package com.example.CourierTrackingSystem.controller;

import com.example.CourierTrackingSystem.dto.CourierDto;
import com.example.CourierTrackingSystem.dto.LocationHistoryDto;
import com.example.CourierTrackingSystem.exception.CourierNotFoundException;
import com.example.CourierTrackingSystem.exception.ReentriesException;
import com.example.CourierTrackingSystem.service.DistanceService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/distance")
@RequiredArgsConstructor
public class DistanceController {

    private final DistanceService distanceService;
    Logger logger = LoggerFactory.getLogger(DistanceController.class);

    @GetMapping("/calculateDistance/{courierId}")
    public ResponseEntity<String> calculateDistance(@PathVariable Long courierId) throws CourierNotFoundException {
        try {
            return ResponseEntity.ok(String.valueOf(distanceService.getCourierTotalTravelDistance(courierId)));
        } catch (RuntimeException e) {
            logger.info("Error occured");
            return ResponseEntity.ok("Error occured");
        }
    }
}
