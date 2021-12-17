package com.example.fishingbooker.Service;

import com.example.fishingbooker.IRepository.ILocationRepository;
import com.example.fishingbooker.IService.ILocationService;
import com.example.fishingbooker.Model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService implements ILocationService {

    @Autowired
    private ILocationRepository locationRepository;

    @Override
    public Location save(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location findByAddress(String address, String city, String country) {
        return locationRepository.findByAddress(address, city, country);
    }
}
