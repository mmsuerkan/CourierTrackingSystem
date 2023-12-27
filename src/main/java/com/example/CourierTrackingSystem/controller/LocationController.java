package com.example.CourierTrackingSystem.controller;

import com.example.CourierTrackingSystem.dto.LocationHistoryDto;
import com.example.CourierTrackingSystem.exception.DistanceException;
import com.example.CourierTrackingSystem.exception.ReentriesException;
import com.example.CourierTrackingSystem.service.LocationService;
import com.example.CourierTrackingSystem.exception.StoreNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/location")
@RequiredArgsConstructor
public class LocationController {


    private final LocationService locationService;
    Logger logger = LoggerFactory.getLogger(LocationController.class);
    @PostMapping("/log")
    public ResponseEntity<String> logLocation(@RequestBody LocationHistoryDto locationHistoryDto) throws StoreNotFoundException, DistanceException {
        try {
            locationService.processLocationLog(locationHistoryDto);
            logger.info("Location logged successfully");
            return ResponseEntity.ok("Location logged successfully");
        } catch (ReentriesException e) {
            logger.info("Reentries to the same store's circumference over 1 minute");
            return ResponseEntity.ok("Reentries to the same store's circumference over 1 minute");
        }
    }
}
