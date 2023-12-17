package com.example.CourierTrackingSystem;

import com.example.CourierTrackingSystem.dto.StoreDto;
import com.example.CourierTrackingSystem.mapper.StoreMapper;
import com.example.CourierTrackingSystem.model.Store;
import com.example.CourierTrackingSystem.repository.StoreRepository;
import com.example.CourierTrackingSystem.service.StoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CourierTrackingSystemApplication implements CommandLineRunner {

	private final StoreMapper storeMapper;
	private final StoreService storeService;

	@Value("${stores.resources.path}")
	private String storesResourcesPath;

	@Autowired
	public CourierTrackingSystemApplication(StoreMapper storeMapper, StoreService storeService) {
		this.storeMapper = storeMapper;
		this.storeService = storeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(CourierTrackingSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Path filePath = Path.of(storesResourcesPath);

		ObjectMapper objectMapper = new ObjectMapper();
		List<StoreDto> storeDtoList = Arrays.asList(objectMapper.readValue(new File(String.valueOf(filePath)), StoreDto[].class));

		List<Store> storeList = storeMapper.dtosToEntities(storeDtoList);

		storeService.saveAllStores(storeList);

	}
}
