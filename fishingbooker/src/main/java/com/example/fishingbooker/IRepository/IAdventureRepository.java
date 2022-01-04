package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAdventureRepository extends JpaRepository<Adventure, Integer> {
    Adventure save(Adventure adventure);

    List<Adventure> findAll();

    @Query("select a from Adventure a where a.owner.id=?1 and a.isDeleted=false")
    List<Adventure> findInstructorAdventures(Integer ownerId);
}
