package com.example.CourierTrackingSystem.service;

import com.example.CourierTrackingSystem.model.Store;
import com.example.CourierTrackingSystem.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

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
