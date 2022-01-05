package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IAdventureRepository extends JpaRepository<Adventure, Integer> {
    Adventure save(Adventure adventure);

    List<Adventure> findAll();

    @Query("select a from Adventure a where a.owner.id=?1 and a.isDeleted=false")
    List<Adventure> findInstructorAdventures(Integer ownerId);

    @Query("update Adventure a set a.isDeleted=true where a.id = ?1")
    @Modifying
    @Transactional
    void deleteAdventure(Integer id);
}
