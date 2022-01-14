package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT r FROM Reservation r WHERE r.reservationEntity.id=?1")
    List<Reservation> findEntityReservations(Integer entityId);

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

}
