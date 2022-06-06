package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Enum.ReservationType;
import com.example.fishingbooker.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT r FROM Reservation r WHERE r.reservationEntity.id=?1")
    List<Reservation> findEntityReservations(Integer entityId);

    @Query("SELECT a FROM Reservation a WHERE a.reservationEntity.id=?1 and a.reservationType=?2")
    List<Reservation> findEntityActions(Integer entityId, ReservationType reservationType);

    @Query("SELECT r FROM Reservation r WHERE r.id=?1")
    Reservation findReservationById(Integer id);

    @Query("SELECT r FROM Reservation r WHERE r.client.id=?1")
    List<Reservation> getClientReservations(Integer clientId);

    @Query("delete from Reservation r where r.id=?1")
    @Modifying
    @Transactional
    void deleteById(Integer id);

    @Query("SELECT r FROM Reservation r WHERE r.client.id=?2 AND ?1 <= r.startDate")
    List<Reservation> getCurrentReservations(Date date, Integer clientId);

    @Modifying
    @Transactional
    @Query("DELETE from Reservation r WHERE r.id=?1")
    void cancelReservation(Integer id);

    @Query("UPDATE Reservation a SET a.isBooked=true, a.client.id=?2 WHERE a.id=?1")
    @Modifying
    @Transactional
    void makeReservation(Integer actionId, Integer clientId);

    @Query("SELECT r FROM Reservation r WHERE (LOWER(r.client.username) LIKE %:username% OR LOWER(r.client.username) LIKE '')")
    List<Reservation> searchClients(@Param("username") String username);

    @Query("SELECT r FROM Reservation r WHERE r.client.id=?1 ORDER BY r.startDate ASC")
    List<Reservation> sortClientReservationsAsc(Integer clientId);

    @Query("SELECT r FROM Reservation r WHERE r.client.id=?1 ORDER BY r.startDate DESC")
    List<Reservation> sortClientReservationsDesc(Integer clientId);

    @Query("SELECT r FROM Reservation r WHERE r.id=?1 AND r.isBooked=true")
    Reservation getReservationById(Integer id);

    @Query("UPDATE Reservation r " +
            "SET r.client.id = ?2, r.isBooked = false " +
            "WHERE r.id = ?1")
    @Modifying
    @Transactional
    void cancelAction(Integer reservationId, Integer ownerId);

    @Query(value = "SELECT count(*) " +
            "FROM Reservation r " +
            "WHERE extract(month from r.startDate) = ?1 " +
                "AND extract(year from r.startDate) = ?2 " +
                "AND r.reservationEntity.owner.id = ?3 ")
    int getReservationNumberByMonth(int month, int year, Integer ownerId);

    @Query(value = "SELECT count(*) " +
            "FROM Reservation r " +
            "WHERE extract(year from r.startDate) = ?1 " +
            "AND r.reservationEntity.owner.id = ?2 ")
    int getReservationNumberByYear(int year, Integer ownerId);

    @Query(value = "SELECT count(*) " +
            "FROM Reservation r " +
            "WHERE extract(day from r.startDate) BETWEEN ?1 AND ?2 " +
            "AND extract(month from r.startDate) = ?3 AND extract(year from r.startDate) = ?4 " +
            "AND r.reservationEntity.owner.id = ?5 ")
    int getReservationNumberBetweenDates(int startDay, int endDay, int month, int year, Integer ownerId);

    @Query(value = "SELECT r " +
            "FROM Reservation r " +
            "WHERE extract(month from r.startDate) = ?1 " +
                "AND extract(year from r.startDate) = ?2 " +
                "AND r.reservationEntity.owner.id = ?3 ")
    List<Reservation> getReservationsOfMonth(int month, int year, Integer ownerId);

    @Query(value = "SELECT coalesce(sum(r.price), 0)  " +
            "FROM Reservation r " +
            "WHERE extract(year from r.startDate) = ?1 AND r.reservationEntity.owner.id = ?2 ")
    double getSalaryByYear(int year, Integer ownerId);
}
