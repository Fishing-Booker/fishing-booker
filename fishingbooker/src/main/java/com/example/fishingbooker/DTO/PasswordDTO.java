package com.example.fishingbooker.DTO;

public class PasswordDTO {
    private String password;
    private Integer id;

    public PasswordDTO() { }

    public PasswordDTO(String password, Integer id) {
        this.password = password;
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
