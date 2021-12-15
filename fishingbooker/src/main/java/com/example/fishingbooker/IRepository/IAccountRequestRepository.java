package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.AccountRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IAccountRequestRepository extends JpaRepository<AccountRequest, Integer> {

    @Query("SELECT r FROM AccountRequest r WHERE r.userId = ?1")
    AccountRequest findByUserId(String username);
}
