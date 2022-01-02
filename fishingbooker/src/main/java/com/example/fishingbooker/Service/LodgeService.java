package com.example.fishingbooker.Service;

import com.example.fishingbooker.IRepository.ILodgeRepository;
import com.example.fishingbooker.IService.ILodgeService;
import com.example.fishingbooker.Model.Lodge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LodgeService implements ILodgeService {

    @Autowired
    private ILodgeRepository lodgeRepository;

    @Override
    public Lodge save(Lodge lodge) {
        return lodgeRepository.save(lodge);
    }

    @Override
    public List<Lodge> findAll() {
        return this.lodgeRepository.findAll();
    }

    @Override
    public List<Lodge> findOwnerLodges(Integer ownerId) {
        List<Lodge> lodges = lodgeRepository.findOwnerLodges(ownerId);
        for (Lodge l : lodges) {
            l.setOwner(null);
            l.setBedrooms(null);
            l.setImages(null);
        }
        return lodges;
    }

}
