package com.example.fishingbooker.Service;

import com.example.fishingbooker.IRepository.IAdventureRepository;
import com.example.fishingbooker.IService.IAdventureService;
import com.example.fishingbooker.Model.Adventure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdventureService implements IAdventureService {
    @Autowired
    private IAdventureRepository adventureRepository;

    @Override
    public Adventure save(Adventure adventure) {
        return adventureRepository.save(adventure);
    }

    @Override
    public List<Adventure> findAll() {
        return adventureRepository.findAll();
    }

    @Override
    public List<Adventure> findInstructorAdventures(Integer ownerId) {
        List<Adventure> adventures = adventureRepository.findInstructorAdventures(ownerId);
        for (Adventure a : adventures) {
            a.setOwner(null);
            a.setImages(null);
        }
        return adventures;
    }

    @Override
    public void deleteAdventure(Integer id) {
        adventureRepository.deleteAdventure(id);
    }
}
