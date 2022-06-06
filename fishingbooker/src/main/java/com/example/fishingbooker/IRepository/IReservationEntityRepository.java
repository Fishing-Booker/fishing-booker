package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.ReservationEntity;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.data.jpa.repository.*;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.List;

public interface IReservationEntityRepository extends JpaRepository<ReservationEntity, Integer> {

    @Query("SELECT e FROM ReservationEntity e WHERE e.id=?1")
    ReservationEntity findEntityById(Integer entityId);

    @Query("SELECT e FROM ReservationEntity e WHERE e.owner.id=?1 and e.isDeleted=false")
    List<ReservationEntity> findOwnerEntities(Integer ownerId);

    @Query("SELECT e.name FROM ReservationEntity e WHERE e.id=?1")
    String getEntityName(Integer entityId);

    @Query("SELECT e FROM ReservationEntity e WHERE e.name=?1 AND e.owner.id=?2")
    ReservationEntity findOwnerEntityByName(String entityName, Integer ownerId);

    @Query("SELECT e.owner.id FROM ReservationEntity e WHERE e.id=?1")
    Integer getOwnerId(Integer entityId);

    @Query("update ReservationEntity r set r.averageGrade=?1 where r.id=?2")
    @Modifying
    @Transactional
    void updateGrade(double averageGrade, Integer entityId);

    @Query("SELECT e FROM ReservationEntity e WHERE e.id = ?1 ")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    ReservationEntity getLocked(Integer id) throws PessimisticLockingFailureException;
}
