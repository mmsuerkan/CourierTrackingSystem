package com.example.CourierTrackingSystem.service;

import com.example.CourierTrackingSystem.model.Courier;
import com.example.CourierTrackingSystem.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourierService {

    private final CourierRepository courierRepository;
    public Courier getCourierById(Long courierId) {
        return courierRepository.findById(courierId).orElseThrow();
    }
}
