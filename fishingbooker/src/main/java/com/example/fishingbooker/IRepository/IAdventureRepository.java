package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
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

    @Query("SELECT DISTINCT SUBSTRING(a.name, 1, 1) AS letters FROM Adventure a WHERE a.isDeleted=false")
    List<String> getFirstLetters();

    @Query("SELECT a FROM Adventure a WHERE (LOWER(a.name) LIKE %:name% OR LOWER(a.name) LIKE '')" +
            "AND (LOWER(a.name) LIKE :letter% OR LOWER(a.name) LIKE '')" +
            "AND (LOWER(a.location.city) LIKE %:location% OR LOWER(a.location.city) LIKE '')" +
            "AND a.averageGrade= :grade " +
            "AND a.isDeleted=false " +
            "ORDER BY a.id")
    List<Adventure> searchAndFilter(@Param("name") String name, @Param("letter") String letter, @Param("location") String location, @Param("grade") double grade);

    @Query("select a from Adventure a where a.id=?1")
    Adventure findAdventureById(Integer id);

    @Query("update Adventure a set a.name=?1, a.description=?2, a.biography=?3, a.maxPersons=?4, a.cancelConditions=?5, a.fishingEquipment=?6 where a.id=?7")
    @Modifying
    @Transactional
    void editAdventure(String name, String description, String biography, Integer maxPersons, double cancelConditions, String fishingEquipment, Integer adventureId);

    @Query("select a.rules from Adventure a where a.id=?1")
    String getAdventureRules(Integer id);

    @Query("update Adventure a set a.rules=?1 where a.id=?2")
    @Modifying
    @Transactional
    void addRule(String rule, Integer adventureId);

    @Query("SELECT a FROM Adventure a " +
            "JOIN ReservationPeriodOwner p ON a.owner.id=p.owner.id " +
            "WHERE ?1 BETWEEN p.startDate AND p.endDate")
    List<Adventure> getByReservationDate(Date date);

    @Query("SELECT a FROM Adventure a WHERE a.isDeleted=false ORDER BY a.name ASC")
    List<Adventure> sortByNameAscending();

    @Query("SELECT a FROM Adventure a WHERE a.isDeleted=false ORDER BY a.name DESC")
    List<Adventure> sortByNameDescending();

    @Query("SELECT a FROM Adventure a WHERE a.isDeleted=false ORDER BY a.averageGrade ASC")
    List<Adventure> sortByGradeAscending();

    @Query("SELECT a FROM Adventure a WHERE a.isDeleted=false ORDER BY a.averageGrade DESC")
    List<Adventure> sortByGradeDescending();
}
