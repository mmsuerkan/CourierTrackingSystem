package com.example.CourierTrackingSystem.service;

import com.example.CourierTrackingSystem.controller.LocationController;
import com.example.CourierTrackingSystem.dto.LocationHistoryDto;
import com.example.CourierTrackingSystem.exception.DistanceException;
import com.example.CourierTrackingSystem.exception.ReentriesException;
import com.example.CourierTrackingSystem.exception.StoreNotFoundException;
import com.example.CourierTrackingSystem.mapper.LocationHistoryMapper;
import com.example.CourierTrackingSystem.model.LocationHistory;
import com.example.CourierTrackingSystem.model.Store;
import com.example.CourierTrackingSystem.repository.LocationHistoryRepository;
import com.example.CourierTrackingSystem.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationHistoryRepository locationHistoryRepository;

    Logger logger = LoggerFactory.getLogger(LocationController.class);
    private final StoreRepository storeRepository;
    private final CourierService courierService;
    private final DistanceService distanceService;
    private final LocationHistoryMapper locationHistoryMapper;

    public void processLocationLog(LocationHistoryDto locationHistoryDto) throws StoreNotFoundException, ReentriesException, RuntimeException, DistanceException {

        List<Store> stores = storeRepository.findAll();
        boolean enter = false;
        if (stores.isEmpty()) {
            throw new StoreNotFoundException("Stores not found");
        }

        for (Store store : stores) {
            if (distanceService.calculateDistance(locationHistoryDto.getLatitude(), locationHistoryDto.getLongitude(),
                    store.getLat(), store.getLng()) < 100) {

                LocalDateTime minuteAgo = locationHistoryDto.getTimestamp().minusMinutes(1);
                if (locationHistoryRepository.countByCourier_IdAndCourier_LocationHistoryList_TimestampAfter(locationHistoryDto.getCourierId(), minuteAgo) == 0) {
                    LocationHistory locationHistory = locationHistoryMapper.dtoToEntity(locationHistoryDto);
                    locationHistory.setCourier(courierService.getCourierById(locationHistoryDto.getCourierId()));
                    locationHistoryRepository.save(locationHistory);
                    enter = true;
                    logger.info("Courier enters radius of 100 meters from Migros Store");
                    break;
                } else {
                    throw new ReentriesException("Reentries to the same store's circumference over 1 minute");
                }
            }
        }
        if (!enter)
            throw new ReentriesException("Courier does not enter radius of 100 meters from Migros");
    }


}
