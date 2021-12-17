package com.example.fishingbooker.Model;

import javax.persistence.*;

@Entity
@Table(name = "delete_request")
@Inheritance(strategy = InheritanceType.JOINED)
public class DeleteAccountRequest {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "delete_reason")
    private String deleteReason;

    @Column(name = "accepted")
    private Boolean isAccepted;

    @Column(name = "disapproved")
    private Boolean isDisapproved;

    public DeleteAccountRequest() {}

    public DeleteAccountRequest(Integer id, String userId, String deleteReason, Boolean isAccepted, Boolean isDisapproved) {
        this.id = id;
        this.userId = userId;
        this.deleteReason = deleteReason;
        this.isAccepted = isAccepted;
        this.isDisapproved = isDisapproved;
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

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    public Boolean getDisapproved() {
        return isDisapproved;
    }

    public void setDisapproved(Boolean disapproved) {
        isDisapproved = disapproved;
    }
}
