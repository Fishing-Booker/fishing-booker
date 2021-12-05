package com.example.fishingbooker.Repository;

import com.example.fishingbooker.Enum.ERole;
import com.example.fishingbooker.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
