package com.example.fishingbooker.Model;

import com.example.fishingbooker.Enum.UserCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shipOwner")
public class ShipOwner extends User{

    @Column(name = "is_capitain")
    private boolean isCaptain;

    @Column(name = "is_officer")
    private boolean isOfficer;

    public ShipOwner() {
    }

    public ShipOwner(Integer id, String username, String password, String name, String surname, String email, String address, String city, String country,
                     String phone, UserCategory userCategory, boolean isCaptain, boolean isOfficer) {
        super(id, username, password, name, surname, email, address, city, country, phone, userCategory);
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
