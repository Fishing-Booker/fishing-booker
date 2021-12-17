package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Bedroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBedroomRepository extends JpaRepository<Bedroom, Integer> {
}
