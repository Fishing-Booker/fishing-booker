package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Lodge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ILodgeRepository extends JpaRepository<Lodge, Integer> {

}
