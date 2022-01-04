package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAdventureRepository extends JpaRepository<Adventure, Integer> {

    @Query("SELECT a from Adventure a WHERE a.isDeleted=false")
    List<Adventure> getAll();

    @Query("SELECT DISTINCT SUBSTRING(a.name, 1, 1) AS letters FROM Adventure a")
    List<String> getFirstLetters();

    @Query("SELECT a FROM Adventure a WHERE (LOWER(a.name) LIKE %:name% OR LOWER(a.name) LIKE '')" +
            "AND (LOWER(a.name) LIKE :letter% OR LOWER(a.name) LIKE '')" +
            "ORDER BY a.id")
    List<Adventure> searchAndFilter(@Param("name") String name, @Param("letter") String letter);
}
