package com.example.fishingbooker.Repository;

import com.example.fishingbooker.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
