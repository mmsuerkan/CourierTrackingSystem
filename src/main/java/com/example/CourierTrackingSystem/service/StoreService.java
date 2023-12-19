package com.example.CourierTrackingSystem.service;

import com.example.CourierTrackingSystem.model.Store;
import com.example.CourierTrackingSystem.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public List<Store> saveAllStores(List<Store> stores) {

        return storeRepository.saveAll(stores);
    }


    public void deleteAllStores() {
        storeRepository.deleteAll();
    }

}
