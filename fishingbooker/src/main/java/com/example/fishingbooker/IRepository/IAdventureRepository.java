package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("SELECT a from Adventure a WHERE a.isDeleted=false")
    List<Adventure> getAll();

    @Query("SELECT DISTINCT SUBSTRING(a.name, 1, 1) AS letters FROM Adventure a")
    List<String> getFirstLetters();

    @Query("SELECT a FROM Adventure a WHERE (LOWER(a.name) LIKE %:name% OR LOWER(a.name) LIKE '')" +
            "AND (LOWER(a.name) LIKE :letter% OR LOWER(a.name) LIKE '')" +
            "ORDER BY a.id")
    List<Adventure> searchAndFilter(@Param("name") String name, @Param("letter") String letter);

}
