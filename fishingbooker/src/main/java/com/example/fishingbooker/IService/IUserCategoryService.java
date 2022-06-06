package com.example.fishingbooker.IService;

import com.example.fishingbooker.Model.UserCategory;

public interface IUserCategoryService {
    void add(UserCategory userCategory);

    void update();

    void updateClientPoints(Integer clientId, double price);

    void updateOwnerPoints(Integer ownerId, double price);
}
