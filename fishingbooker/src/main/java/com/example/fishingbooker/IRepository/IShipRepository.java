package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface IShipRepository extends JpaRepository<Ship, Integer> {

    @Query("SELECT s FROM Ship s WHERE s.owner.id=?1 and s.isDeleted=false")
    List<Ship> findOwnerShips(Integer ownerId);

    @Query("update Ship s set s.isDeleted=true WHERE s.id=?1")
    @Modifying
    @Transactional
    void deleteShip(Integer shipId);

    @Query("SELECT s FROM Ship s WHERE s.id=?1")
    Ship findShipById(Integer id);

    @Query("update Ship s " +
            "set s.name=?1, s.description=?2, s.shipType=?3, s.length=?4, s.engineNumber=?5, s.enginePower=?6, s.maxSpeed=?7, s.maxPersons=?8 " +
            "WHERE s.id=?9")
    @Modifying
    @Transactional
    void updateShip(String name, String description, String shipType, double length, Integer engineNumber, double enginePower, double maxSpeed, Integer capacity, Integer shipId);

    @Query("SELECT s.rules FROM Ship s WHERE s.id=?1 ")
    String findShipRules(Integer shipId);

    @Query("update Ship s set s.rules=?1 WHERE s.id=?2")
    @Modifying
    @Transactional
    void addRule(String rule, Integer shipId);

    @Query("SELECT s FROM Ship s WHERE s.isDeleted=false")
    List<Ship> getAll();

    @Query("SELECT DISTINCT SUBSTRING(s.name, 1, 1) AS letters FROM Ship s WHERE s.isDeleted=false")
    List<String> getFirstLetters();

    @Query("SELECT s FROM Ship s WHERE (LOWER(s.name) LIKE %:name% OR LOWER(s.name) LIKE '')" +
            "AND (LOWER(s.name) LIKE :letter% OR LOWER(s.name) LIKE '')" +
            "AND (LOWER(s.location.city) LIKE %:location% OR LOWER(s.location.city) LIKE '') " +
            "AND s.averageGrade= :grade " +
            "AND s.isDeleted=false " +
            "ORDER BY s.id")
    List<Ship> searchAndFilter(@Param("name") String name, @Param("letter") String letter, @Param("location") String location, @Param("grade") double grade);

    @Query("SELECT s FROM ReservationPeriodOwner p " +
            "JOIN Ship s ON p.owner.id=s.owner.id " +
            "WHERE ?1 BETWEEN p.startDate AND p.endDate")
    List<Ship> getByReservationDate(Date date);

    @Query("SELECT s.navigationEquipment FROM Ship s WHERE s.id=?1 ")
    String findShipNavEquipment(Integer shipId);

    @Query("update Ship s set s.navigationEquipment=?1 WHERE s.id=?2")
    @Modifying
    @Transactional
    void addNavEquipment(String equipment, Integer shipId);

    @Query("SELECT s.name FROM Ship s WHERE s.owner.id=?1 and s.isDeleted=false")
    List<String> getOwnerShipNames(Integer ownerId);

    @Query("SELECT s FROM Ship s WHERE (LOWER(s.name) LIKE %:name% OR LOWER(s.name) LIKE '')")
    List<Ship> searchByName(@Param("name") String name);

    @Query("SELECT s FROM Ship s WHERE s.isDeleted=false ORDER BY s.name ASC")
    List<Ship> sortByNameAscending();

    @Query("SELECT s FROM Ship s WHERE s.isDeleted=false ORDER BY s.name DESC")
    List<Ship> sortByNameDescending();

    @Query("SELECT s FROM Ship s WHERE s.isDeleted=false ORDER BY s.averageGrade ASC")
    List<Ship> sortByGradeAscending();

    @Query("SELECT s FROM Ship s WHERE s.isDeleted=false ORDER BY s.averageGrade DESC")
    List<Ship> sortByGradeDescending();
}
