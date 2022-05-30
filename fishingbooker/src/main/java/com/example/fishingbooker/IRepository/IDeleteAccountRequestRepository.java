package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.DeleteAccountRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IDeleteAccountRequestRepository extends JpaRepository<DeleteAccountRequest, Integer> {
    DeleteAccountRequest save(DeleteAccountRequest deleteAccountRequest);
    List<DeleteAccountRequest> findAll();

    @Query("select req from DeleteAccountRequest req where req.isDisapproved = false and req.isAccepted = false")
    List<DeleteAccountRequest> findAllUnprocessedRequests();

    @Query("delete from DeleteAccountRequest req where req.id=?1")
    @Modifying
    @Transactional
    void deleteById(Integer id);

    @Query("UPDATE DeleteAccountRequest req SET req.isAccepted = true WHERE req.id = ?1")
    @Modifying
    @Transactional
    void approve(Integer id);

    @Query("UPDATE DeleteAccountRequest req SET req.isDisapproved = true WHERE req.id = ?1")
    @Modifying
    @Transactional
    void disapprove(Integer id);

}
