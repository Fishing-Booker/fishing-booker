package com.example.fishingbooker.IService;

import com.example.fishingbooker.Model.Location;

public interface ILocationService {
    Location save(Location location);

    Location findByAddress(String address, String city, String country);

    void updateLocation(String address, String city, String country, Integer locationId);
}
