package com.example.fishingbooker.DTO.reservationAction;

public class MakeReservationDTO {
    private Integer actionId;
    private Integer clientId;

    public MakeReservationDTO() {
    }

    public MakeReservationDTO(Integer actionId, Integer clientId) {
        this.actionId = actionId;
        this.clientId = clientId;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
}
