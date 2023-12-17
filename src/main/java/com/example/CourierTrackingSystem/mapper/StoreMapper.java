package com.example.CourierTrackingSystem.mapper;

import com.example.CourierTrackingSystem.dto.StoreDto;
import com.example.CourierTrackingSystem.model.Store;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true)
    })
    Store dtoToEntity(StoreDto storeDto);

    List<Store> dtosToEntities(List<StoreDto> storeDtos);
}
