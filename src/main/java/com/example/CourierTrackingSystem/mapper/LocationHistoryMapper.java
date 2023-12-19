package com.example.CourierTrackingSystem.mapper;

import com.example.CourierTrackingSystem.dto.LocationHistoryDto;
import com.example.CourierTrackingSystem.dto.StoreDto;
import com.example.CourierTrackingSystem.model.LocationHistory;
import com.example.CourierTrackingSystem.model.Store;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationHistoryMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    LocationHistory dtoToEntity(LocationHistoryDto locationHistoryDto);

    List<LocationHistory> dtosToEntities(List<LocationHistoryDto> locationHistoryDtos);

    LocationHistoryDto entityToDto(LocationHistory locationHistory);
}