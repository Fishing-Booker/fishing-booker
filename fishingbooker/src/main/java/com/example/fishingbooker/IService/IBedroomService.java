package com.example.fishingbooker.IService;

import com.example.fishingbooker.Model.Bedroom;

import java.util.List;

public interface IBedroomService {
    Bedroom save(Bedroom bedroom);

    List<Bedroom> findAll();

    List<Bedroom> findLodgeBedrooms(Integer lodgeId);

    void updateBedroom(Integer oneBed, Integer twoBed, Integer threeBed, Integer fourBed, Integer lodgeId);
}
