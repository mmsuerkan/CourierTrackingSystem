package com.example.CourierTrackingSystem.service;

import com.example.CourierTrackingSystem.exception.CourierNotFoundException;
import com.example.CourierTrackingSystem.model.Courier;
import com.example.CourierTrackingSystem.model.LocationHistory;
import com.example.CourierTrackingSystem.repository.LocationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistanceService {

    private static final double EARTH_RADIUS = 6371.0;

    private final LocationHistoryRepository locationHistoryRepository;

    private final CourierService courierService;

    public DistanceService() {
        this.locationHistoryRepository = null;
        this.courierService = null;
    }

    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Convert latitude and longitude from degrees to radians
        double radLat1 = Math.toRadians(lat1);
        double radLon1 = Math.toRadians(lon1);
        double radLat2 = Math.toRadians(lat2);
        double radLon2 = Math.toRadians(lon2);

        // Calculate differences in coordinates
        double deltaLat = radLat2 - radLat1;
        double deltaLon = radLon2 - radLon1;

        // Haversine formula
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(radLat1) * Math.cos(radLat2) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculate distance
        double distance = EARTH_RADIUS * c;

        return distance * 1000;
    }

    public double getCourierTotalTravelDistance(Long courierId) throws CourierNotFoundException {

        Courier courier = courierService.getCourierById(courierId);

        if (courier == null) {
            throw new CourierNotFoundException("Courier not found");
        }

        List<LocationHistory> locationHistories = locationHistoryRepository.findByCourierIdAllIgnoreCaseOrderByTimestampAsc(courier.getId(), null);

        double totalDistance = 0.0;
        if(locationHistories.isEmpty()) {
            return 0;
        }

        for (int i = 0; i < locationHistories.size() - 1; i++) {
            totalDistance += calculateDistance(locationHistories.get(i).getLatitude(), locationHistories.get(i).getLongitude(),
                    locationHistories.get(i + 1).getLatitude(), locationHistories.get(i + 1).getLongitude());
        }

        return totalDistance;
    }
}
