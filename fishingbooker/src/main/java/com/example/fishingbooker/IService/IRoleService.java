package com.example.fishingbooker.IService;

import com.example.fishingbooker.Model.Role;

import java.util.List;

public interface IRoleService {
    Role findById(Integer id);
    List<Role> findByName(String name);
}
