package com.example.fishingbooker.Service;

import com.example.fishingbooker.IRepository.IBedroomRepository;
import com.example.fishingbooker.IService.IBedroomService;
import com.example.fishingbooker.Model.Bedroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BedroomService implements IBedroomService {

    @Autowired
    private IBedroomRepository bedroomRepository;

    @Override
    public Bedroom save(Bedroom bedroom) {
        return bedroomRepository.save(bedroom);
    }
}
