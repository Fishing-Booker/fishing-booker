package com.example.fishingbooker.DTO.lodge;

public class OwnerDTO {
    String name;
    String surname;

    public OwnerDTO() {
    }

    public OwnerDTO(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
