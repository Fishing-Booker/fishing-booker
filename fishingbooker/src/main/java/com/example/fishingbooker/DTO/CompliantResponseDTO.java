package com.example.fishingbooker.DTO;

public class CompliantResponseDTO {
    private Integer clientId;
    private Integer ownerId;
    private String text;
    private Integer compliantId;

    public CompliantResponseDTO() {
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCompliantId() {
        return compliantId;
    }

    public void setCompliantId(Integer compliantId) {
        this.compliantId = compliantId;
    }
}
