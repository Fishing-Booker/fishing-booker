package com.example.fishingbooker.DTO.fishingEquipment;

public class AddFishingEquipmentDTO {

    private Integer ownerId;
    private Integer entityId;
    private String name;

    public AddFishingEquipmentDTO() {
    }

    public AddFishingEquipmentDTO(Integer ownerId, Integer entityId, String name) {
        this.ownerId = ownerId;
        this.entityId = entityId;
        this.name = name;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
