package com.example.fishingbooker.DTO;

public class ComplaintDTO {
    private Integer clientId;
    private Integer entityId;
    private String text;

    public ComplaintDTO() {
    }

    public ComplaintDTO(Integer clientId, Integer entityId, String text) {
        this.clientId = clientId;
        this.entityId = entityId;
        this.text = text;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
