package com.example.CourierTrackingSystem.repository;

import com.example.CourierTrackingSystem.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
}
