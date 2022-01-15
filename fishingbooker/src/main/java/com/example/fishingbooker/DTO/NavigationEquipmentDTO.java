package com.example.fishingbooker.DTO;

public class NavigationEquipmentDTO {

    private String name;
    private boolean exists;

    public NavigationEquipmentDTO() {
    }

    public NavigationEquipmentDTO(String name, boolean exists) {
        this.name = name;
        this.exists = exists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
