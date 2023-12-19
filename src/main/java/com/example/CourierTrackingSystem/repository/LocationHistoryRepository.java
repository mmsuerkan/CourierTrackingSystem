package com.example.CourierTrackingSystem.repository;

import com.example.CourierTrackingSystem.model.LocationHistory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface LocationHistoryRepository extends JpaRepository<LocationHistory, Long> {




    long countByCourier_IdAndCourier_LocationHistoryList_TimestampAfter(Long id, LocalDateTime timestamp);

    List<LocationHistory> findByCourierIdAllIgnoreCaseOrderByTimestampAsc(Long id, Sort sort);


}
