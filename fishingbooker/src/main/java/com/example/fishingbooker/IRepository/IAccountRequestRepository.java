package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.AccountRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IAccountRequestRepository extends JpaRepository<AccountRequest, Integer> {

    @Query("SELECT r FROM AccountRequest r WHERE r.userId = ?1")
    AccountRequest findByUserId(String username);

    List<AccountRequest> findAll();

    @Query("delete from AccountRequest req where req.userId=?1")
    @Modifying
    @Transactional
    void deleteById(String username);
}
