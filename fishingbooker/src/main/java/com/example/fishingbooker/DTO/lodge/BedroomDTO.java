package com.example.fishingbooker.DTO.lodge;

public class BedroomDTO {
    String bedroomType;
    Integer roomNumber;

    public BedroomDTO() {
    }

    public BedroomDTO(String bedroomType, Integer roomNumber) {
        this.bedroomType = bedroomType;
        this.roomNumber = roomNumber;
    }

    public String getBedroomType() {
        return bedroomType;
    }

    public void setBedroomType(String bedroomType) {
        this.bedroomType = bedroomType;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }
}
