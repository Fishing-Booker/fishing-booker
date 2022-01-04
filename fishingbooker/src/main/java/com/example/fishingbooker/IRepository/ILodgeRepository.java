package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.DTO.UpdateLodgeDTO;
import com.example.fishingbooker.Model.Lodge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ILodgeRepository extends JpaRepository<Lodge, Integer> {

    @Query("SELECT l FROM Lodge l WHERE l.owner.id=?1 and l.isDeleted=false")
    List<Lodge> findOwnerLodges(Integer ownerId);

    @Query("SELECT l FROM Lodge l WHERE l.id=?1")
    Lodge findLodgeById(Integer id);

    @Query("update Lodge l set l.isDeleted=true WHERE l.id=?1")
    @Modifying
    @Transactional
    void deleteLodge(Integer lodgeId);

    @Query("SELECT l.rules FROM Lodge l WHERE l.id=?1 ")
    String findLodgeRules(Integer lodgeId);

    @Query("update Lodge l set l.rules=?1 WHERE l.id=?2")
    @Modifying
    @Transactional
    void addRule(String rule, Integer lodgeId);

    @Query("update Lodge l " + "set l.name=?1, l.description=?2 " + "WHERE l.id=?3")
    @Modifying
    @Transactional
    void updateLodge(String name, String description, Integer lodgeId);

    @Query("SELECT l FROM Lodge l WHERE (LOWER(l.name) LIKE %:name% OR LOWER(l.name) LIKE '') " +
            "AND (LOWER(l.name) LIKE :letter% OR LOWER(l.name) LIKE '') ORDER BY l.id")
    List<Lodge> search(@Param("name") String name, @Param("letter") String letter);

    @Query("SELECT DISTINCT SUBSTRING(l.name, 1, 1) AS letters FROM Lodge l")
    List<String> getFirstLetters();
}
