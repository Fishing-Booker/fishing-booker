package com.example.fishingbooker.DTO.fishingEquipment;

public class FishingEquipmentDTO {

    private Integer entityId;
    private String equipment;

    public FishingEquipmentDTO() {
    }

    public FishingEquipmentDTO(Integer entityId, String equipment) {
        this.entityId = entityId;
        this.equipment = equipment;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}
