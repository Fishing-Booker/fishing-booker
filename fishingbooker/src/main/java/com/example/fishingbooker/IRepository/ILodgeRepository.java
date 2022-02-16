package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Lodge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
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

    @Query("update Lodge l " + "set l.name=?1, l.maxPersons=?2, l.description=?3, l.cancelConditions=?4 " + "WHERE l.id=?5")
    @Modifying
    @Transactional
    void updateLodge(String name, Integer maxPersons, String description, double cancelConditions, Integer lodgeId);

    @Query("SELECT l FROM Lodge l WHERE (LOWER(l.name) LIKE %:name% OR LOWER(l.name) LIKE '') " +
            "AND (LOWER(l.location.city) LIKE %:location% OR LOWER(l.location.city) LIKE '')" +
            "AND (LOWER(l.name) LIKE :letter% OR LOWER(l.name) LIKE '') " +
            "ORDER BY l.id")
    List<Lodge> search(@Param("name") String name, @Param("letter") String letter, @Param("location") String location);

    @Query("SELECT DISTINCT SUBSTRING(l.name, 1, 1) AS letters FROM Lodge l")
    List<String> getFirstLetters();

    @Query("SELECT l FROM Lodge l WHERE l.isDeleted=false")
    List<Lodge> getAll();

    @Query("SELECT l FROM Lodge l WHERE l.id=?1 AND l.isDeleted=false")
    Lodge getLodgeById(Integer id);

    @Query("SELECT l.name FROM Lodge l WHERE l.owner.id=?1 and l.isDeleted=false")
    List<String> getOwnerLodgeNames(Integer ownerId);

    @Query("SELECT l FROM Lodge l " +
            "JOIN ReservationPeriod p ON l.id=p.reservationEntity.id" +
            " WHERE ?1 BETWEEN p.startDate AND p.endDate")
    List<Lodge> getByReservationDate(Date date);

    @Query("SELECT l FROM Lodge l WHERE (LOWER(l.name) LIKE %:name% OR LOWER(l.name) LIKE '')")
    List<Lodge> searchByName(@Param("name") String name);

}
