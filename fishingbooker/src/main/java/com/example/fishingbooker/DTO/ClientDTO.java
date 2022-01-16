package com.example.fishingbooker.DTO;

public class ClientDTO {
    private Integer id;
    private String name;

    public ClientDTO() {
    }

    public ClientDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
