package com.example.fishingbooker.DTO;

import javax.persistence.criteria.CriteriaBuilder;

public class SubscriberDTO {
    private Integer entityId;
    private Integer userId;

    public SubscriberDTO() {
    }

    public SubscriberDTO(Integer entityId, Integer userId) {
        this.entityId = entityId;
        this.userId = userId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
