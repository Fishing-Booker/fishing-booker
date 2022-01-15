package com.example.fishingbooker.DTO.reservation;

public class ActiveReservationDTO {
    private Integer id;
    private String name;
    private Integer maxPersons;

    public ActiveReservationDTO() {
    }

    public ActiveReservationDTO(Integer id, String name, Integer maxPersons) {
        this.id = id;
        this.name = name;
        this.maxPersons = maxPersons;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }
}
