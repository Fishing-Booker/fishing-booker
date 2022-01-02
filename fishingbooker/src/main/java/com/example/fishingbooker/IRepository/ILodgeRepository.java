package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Lodge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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

}
