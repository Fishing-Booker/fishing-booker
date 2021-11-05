package com.example.fishingbooker.Model;

import com.example.fishingbooker.Enum.UserCategory;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shipOwner")
public class ShipOwner extends User{

    private boolean isCaptain;
    private boolean isOfficer;

    public ShipOwner() {
    }

    public ShipOwner(String username, String password, String name, String surname, String email, String address, String city, String country,
                     String phone, UserCategory userCategory, boolean isCaptain, boolean isOfficer) {
        super(username, password, name, surname, email, address, city, country, phone, userCategory);
        this.isCaptain = isCaptain;
        this.isOfficer = isOfficer;
    }

    public boolean isCaptain() {
        return isCaptain;
    }

    public void setCaptain(boolean captain) {
        isCaptain = captain;
    }

    public boolean isOfficer() {
        return isOfficer;
    }

    public void setOfficer(boolean officer) {
        isOfficer = officer;
    }
}
