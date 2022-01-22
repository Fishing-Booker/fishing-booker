package com.example.fishingbooker.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "adventure")
public class Adventure extends ReservationEntity {

    @Column(name = "biography")
    private String biography;

    @Column(name = "fishing_equipment")
    private String fishingEquipment;

    public Adventure() {
    }

    public Adventure(Integer id, User owner, String name, Location location, String description, String rules,
                     double cancelConditions, double averageGrade, String biography, Integer maxPersons, List<Image> images, String fishingEquipment) {
        super(id, owner, name, location, description, rules, cancelConditions, averageGrade, maxPersons, images);
        this.biography = biography;
        this.fishingEquipment = fishingEquipment;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(String fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }
}
