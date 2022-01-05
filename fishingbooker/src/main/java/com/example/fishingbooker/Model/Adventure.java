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

    public Adventure() {
    }

    public Adventure(Integer id, User owner, String name, Location location, String description, String rules,
                     String cancelConditions, double averageGrade, String biography, Integer maxPersons, List<Image> images) {
        super(id, owner, name, location, description, rules, cancelConditions, averageGrade, maxPersons, images);
        this.biography = biography;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
