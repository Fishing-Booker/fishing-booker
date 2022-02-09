package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserCategoryRepository extends JpaRepository<UserCategory, Integer> {
    UserCategory save(UserCategory userCategory);
}
