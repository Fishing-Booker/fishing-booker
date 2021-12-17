package com.example.fishingbooker.Service;

import com.example.fishingbooker.IRepository.ILodgeRepository;
import com.example.fishingbooker.IService.ILodgeService;
import com.example.fishingbooker.Model.Lodge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LodgeService implements ILodgeService {

    @Autowired
    private ILodgeRepository lodgeRepository;

    @Override
    public Lodge save(Lodge lodge) {
        return lodgeRepository.save(lodge);
    }

}
