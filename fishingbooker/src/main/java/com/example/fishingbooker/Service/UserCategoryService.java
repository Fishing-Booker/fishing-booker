package com.example.fishingbooker.Service;

import com.example.fishingbooker.Enum.CategoryType;
import com.example.fishingbooker.IRepository.IUserCategoryRepository;
import com.example.fishingbooker.IService.ILoyaltyProgrammeService;
import com.example.fishingbooker.IService.IUserCategoryService;
import com.example.fishingbooker.Model.LoyaltyProgramme;
import com.example.fishingbooker.Model.User;
import com.example.fishingbooker.Model.UserCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCategoryService implements IUserCategoryService {

    @Autowired
    IUserCategoryRepository userCategoryRepository;

    @Autowired
    ILoyaltyProgrammeService loyaltyProgrammeService;

    @Override
    public void add(UserCategory userCategory) {
        userCategoryRepository.save(userCategory);
    }

    @Override
    public void update() {
        List<UserCategory> categories = userCategoryRepository.findAll();
        LoyaltyProgramme loyaltyProgramme = loyaltyProgrammeService.get();
        for (UserCategory category : categories) {
            if(category.getPoints() < loyaltyProgramme.getBronzeLimit()){
                category.setCategoryType(CategoryType.wood);
            } else if(category.getPoints() >= loyaltyProgramme.getBronzeLimit()
                    && category.getPoints() < loyaltyProgramme.getSilverLimit()) {
                category.setCategoryType(CategoryType.bronze);
            } else if(category.getPoints() >= loyaltyProgramme.getSilverLimit()
                    && category.getPoints() < loyaltyProgramme.getGoldLimit()){
                category.setCategoryType(CategoryType.silver);
            } else {
                category.setCategoryType(CategoryType.gold);
            }
            userCategoryRepository.save(category);
        }
    }

    @Override
    public void updateClientPoints(Integer clientId, double price) {
        UserCategory userCategory = userCategoryRepository.getUserCategoryByClientId(clientId);
        LoyaltyProgramme loyaltyProgramme = loyaltyProgrammeService.get();
        userCategory.setPoints(userCategory.getPoints() + price * loyaltyProgramme.getClientIncome()/100);
        userCategory.setCategoryType(updateCategory(userCategory));
        userCategoryRepository.save(userCategory);
    }

    @Override
    public void updateOwnerPoints(Integer ownerId, double price) {
        UserCategory userCategory = userCategoryRepository.getUserCategoryByClientId(ownerId);
        LoyaltyProgramme loyaltyProgramme = loyaltyProgrammeService.get();
        userCategory.setPoints(userCategory.getPoints() + price * loyaltyProgramme.getClientIncome()/100);
        userCategory.setCategoryType(updateCategory(userCategory));
        userCategoryRepository.save(userCategory);
    }

    @Override
    public UserCategory get(Integer clientId) {
        return userCategoryRepository.getUserCategoryByClientId(clientId);
    }

    private CategoryType updateCategory(UserCategory category){
        LoyaltyProgramme loyaltyProgramme = loyaltyProgrammeService.get();
        if(category.getPoints() < loyaltyProgramme.getBronzeLimit()){
            return CategoryType.wood;
        } else if(category.getPoints() >= loyaltyProgramme.getBronzeLimit()
                && category.getPoints() < loyaltyProgramme.getSilverLimit()) {
            return CategoryType.bronze;
        } else if(category.getPoints() >= loyaltyProgramme.getSilverLimit()
                && category.getPoints() < loyaltyProgramme.getGoldLimit()){
            return CategoryType.silver;
        } else {
            return CategoryType.gold;
        }
    }


}
