package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.SystemIncomeDTO;
import com.example.fishingbooker.Enum.CategoryType;
import com.example.fishingbooker.IRepository.IOwnerIncomeRepository;
import com.example.fishingbooker.IRepository.IUserCategoryRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.ILoyaltyProgrammeService;
import com.example.fishingbooker.IService.IOwnerIncomeService;
import com.example.fishingbooker.Model.OwnerIncome;
import com.example.fishingbooker.Model.User;
import com.example.fishingbooker.Model.UserCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerIncomeService implements IOwnerIncomeService {

    @Autowired
    private IOwnerIncomeRepository ownerIncomeRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ILoyaltyProgrammeService loyaltyProgrammeService;

    @Autowired
    private IUserCategoryRepository userCategoryRepository;

    @Override
    public void initializeOwnerIncome(String ownerUsername) {
        User owner = userRepository.findByUsername(ownerUsername);
        OwnerIncome ownerIncome = new OwnerIncome();
        ownerIncome.setIncome(0);
        ownerIncome.setOwner(owner);
        ownerIncomeRepository.save(ownerIncome);
    }

    @Override
    public void updateOwnerIncome(Integer id, double price) {
        OwnerIncome ownerIncome = ownerIncomeRepository.findOwnerIncomeByOwnerId(id);
        ownerIncome.setIncome(ownerIncome.getIncome() + calculateOwnerIncome(id, price));
        ownerIncome.setSystemIncome(ownerIncome.getSystemIncome() + calculateSystemIncome(id, price));
        ownerIncomeRepository.save(ownerIncome);
    }

    @Override
    public SystemIncomeDTO getSystemIncome() {
        List<OwnerIncome> incomes = ownerIncomeRepository.findAll();
        double sum = 0;
        for (OwnerIncome ownerIncome : incomes) {
            sum += ownerIncome.getSystemIncome();
        }
        return new SystemIncomeDTO(sum);
    }

    private double calculateOwnerIncome(Integer id, double price){
        UserCategory userCategory = userCategoryRepository.getUserCategoryByClientId(id);
        if(userCategory.getCategoryType() == CategoryType.wood){
            return price * 0.9;
        } else if(userCategory.getCategoryType() == CategoryType.bronze){
            return price * 0.93;
        } else if(userCategory.getCategoryType() == CategoryType.silver){
            return price * 0.95;
        } else {
            return price * 0.98;
        }
    }

    private double calculateSystemIncome(Integer id, double price){
        UserCategory userCategory = userCategoryRepository.getUserCategoryByClientId(id);
        if(userCategory.getCategoryType() == CategoryType.wood){
            //systemIncome = price * 0.1
            return price * 0.1;
        } else if(userCategory.getCategoryType() == CategoryType.bronze){
            return price * 0.07;
        } else if(userCategory.getCategoryType() == CategoryType.silver){
            return price * 0.05;
        } else {
            return price * 0.02;
        }
    }
}
