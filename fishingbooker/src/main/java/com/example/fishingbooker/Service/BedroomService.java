package com.example.fishingbooker.Service;

import com.example.fishingbooker.IRepository.IBedroomRepository;
import com.example.fishingbooker.IService.IBedroomService;
import com.example.fishingbooker.Model.Bedroom;
import com.example.fishingbooker.Model.Lodge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BedroomService implements IBedroomService {

    @Autowired
    private IBedroomRepository bedroomRepository;

    @Override
    public Bedroom save(Bedroom bedroom) {
        return bedroomRepository.save(bedroom);
    }

    @Override
    public List<Bedroom> findAll() {
        return bedroomRepository.findAll();
    }

    @Override
    public List<Bedroom> findLodgeBedrooms(Integer lodgeId) {
        List<Bedroom> bedrooms =  bedroomRepository.findLodgeBedrooms(lodgeId);
        List<Bedroom> availableBedrooms = new ArrayList<>();
        for (Bedroom b: bedrooms) {
            if(b.getRoomNumber() > 0){
                b.setLodge(null);
                availableBedrooms.add(b);
            }
        }
        return availableBedrooms;
    }
}
