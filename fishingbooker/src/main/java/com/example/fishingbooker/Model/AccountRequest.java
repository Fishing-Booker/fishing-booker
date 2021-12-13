package com.example.fishingbooker.Model;

import javax.persistence.*;

@Entity
@Table(name="account_request")
@Inheritance(strategy = InheritanceType.JOINED)
public class AccountRequest {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "registration_reason")
    private String registrationReason;

    public AccountRequest() { }

    public AccountRequest(Integer id, String userId, String registrationReason) {
        this.id = id;
        this.userId = userId;
        this.registrationReason = registrationReason;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRegistrationReason() {
        return registrationReason;
    }

    public void setRegistrationReason(String registrationReason) {
        this.registrationReason = registrationReason;
    }
}
