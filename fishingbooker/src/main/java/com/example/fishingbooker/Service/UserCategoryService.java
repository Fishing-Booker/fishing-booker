package com.example.fishingbooker.Service;

import com.example.fishingbooker.IRepository.IUserCategoryRepository;
import com.example.fishingbooker.IService.IUserCategoryService;
import com.example.fishingbooker.Model.UserCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCategoryService implements IUserCategoryService {

    @Autowired
    IUserCategoryRepository userCategoryRepository;

    @Override
    public void add(UserCategory userCategory) {
        userCategoryRepository.save(userCategory);
    }
}
