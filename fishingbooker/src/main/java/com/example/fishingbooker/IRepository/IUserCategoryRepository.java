package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.User;
import com.example.fishingbooker.Model.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IUserCategoryRepository extends JpaRepository<UserCategory, Integer> {
    UserCategory save(UserCategory userCategory);

    @Query("SELECT userCategory from UserCategory userCategory WHERE userCategory.user.id=?1")
    UserCategory getUserCategoryByClientId(Integer clientId);
}
