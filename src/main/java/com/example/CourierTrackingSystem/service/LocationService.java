package com.example.CourierTrackingSystem.service;

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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationHistoryRepository locationHistoryRepository;


    private final StoreRepository storeRepository;
    private final CourierService courierService;
    private final DistanceService distanceService;
    private final LocationHistoryMapper locationHistoryMapper;

    public void processLocationLog(LocationHistoryDto locationHistoryDto) throws StoreNotFoundException, ReentriesException, RuntimeException {

        List<Store> stores = storeRepository.findAll();

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
                } else {
                    throw new ReentriesException("Reentries to the same store's circumference over 1 minute");
                }
                break;
            } else {
                LocationHistory locationHistory = locationHistoryMapper.dtoToEntity(locationHistoryDto);
                locationHistory.setCourier(courierService.getCourierById(locationHistoryDto.getCourierId()));
                locationHistoryRepository.save(locationHistory);
                break;
            }
        }
    }


}
