package com.example.fishingbooker.IService;

import com.example.fishingbooker.Model.Lodge;

import java.util.List;

public interface ILodgeService {
    Lodge save(Lodge lodge);
    List<Lodge> getAll();
}
