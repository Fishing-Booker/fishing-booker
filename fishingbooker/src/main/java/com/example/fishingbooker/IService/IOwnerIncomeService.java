package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.SystemIncomeDTO;

public interface IOwnerIncomeService {
    void initializeOwnerIncome(String ownerUsername);

    void updateOwnerIncome(Integer id, double price);

    SystemIncomeDTO getSystemIncome();
}
