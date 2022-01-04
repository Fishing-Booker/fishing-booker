package com.example.fishingbooker.DTO;

public class NavigationEquipmentDTO {

    private Integer owner;
    private String equipment;

    public NavigationEquipmentDTO() {
    }

    public NavigationEquipmentDTO(Integer owner, String equipment) {
        this.owner = owner;
        this.equipment = equipment;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}
