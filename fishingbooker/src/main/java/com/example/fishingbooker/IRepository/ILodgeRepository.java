package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Lodge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILodgeRepository extends JpaRepository<Lodge, Integer> {

    @Query("SELECT l FROM Lodge l WHERE l.owner.id=?1")
    List<Lodge> findOwnerLodges(Integer ownerId);

}
