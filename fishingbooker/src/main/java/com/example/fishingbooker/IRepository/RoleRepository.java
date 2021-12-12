package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Enum.ERole;
import com.example.fishingbooker.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByName(ERole name);
    Role getOne(Integer id);
}
