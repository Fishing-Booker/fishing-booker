package com.example.fishingbooker.IService;

import com.example.fishingbooker.Enum.ERole;
import com.example.fishingbooker.IRepository.RoleRepository;
import com.example.fishingbooker.Model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findById(Integer id) {
        Role auth = this.roleRepository.getOne(id);
        return auth;
    }

    @Override
    public List<Role> findByName(String name) {
        ERole role;
        if(name == ERole.admin.toString()) {
            role = ERole.admin;
        } else if (name == ERole.client.toString()) {
            role = ERole.client;
        } else if(name == ERole.lodgeOwner.toString()){
            role = ERole.lodgeOwner;
        } else if(name == ERole.instructor.toString()) {
            role = ERole.instructor;
        } else if(name == ERole.shipOwner.toString()) {
            role = ERole.shipOwner;
        } else{
            role = ERole.defaultAdmin;
        }

        List<Role> roles = this.roleRepository.findByName(role);
        return roles;
    }

}
