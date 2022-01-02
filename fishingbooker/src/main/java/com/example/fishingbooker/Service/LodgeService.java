package com.example.fishingbooker.Service;

import com.example.fishingbooker.IRepository.ILodgeRepository;
import com.example.fishingbooker.IService.ILodgeService;
import com.example.fishingbooker.Model.Lodge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LodgeService implements ILodgeService {

    @Autowired
    private ILodgeRepository lodgeRepository;

    @Autowired
    private BedroomService bedroomService;

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

    @Override
    public void deleteLodge(Integer lodgeId) {
        lodgeRepository.deleteLodge(lodgeId);
    }

    @Override
    public Lodge findById(Integer lodgeId) {
        Lodge lodge = lodgeRepository.findLodgeById(lodgeId);
        lodge.setOwner(null);
        lodge.setImages(null);
        lodge.setBedrooms(bedroomService.findLodgeBedrooms(lodgeId));
        return lodge;
    }

    @Override
    public List<String> findLodgeRules(Integer lodgeId) {
        String rules = lodgeRepository.findLodgeRules(lodgeId);
        return new ArrayList<>(Arrays.asList(rules.split("#")));
    }

    @Override
    public void addRule(String rule, Integer lodgeId) {
        String rules = lodgeRepository.findLodgeRules(lodgeId);
        String newRule = rules + "#" + rule;
        lodgeRepository.addRule(newRule, lodgeId);
    }

    @Override
    public void deleteRule(Integer ruleIndex, Integer lodgeId) {
        String[] rules = lodgeRepository.findLodgeRules(lodgeId).split("#");
        rules[ruleIndex] = "";
        StringBuilder newRules = new StringBuilder();
        for (String rule : rules) {
            rule = rule.replace("#", "");
            newRules.append(rule);
            newRules.append("#");
        }
        lodgeRepository.addRule(String.valueOf(newRules), lodgeId);
    }

}
