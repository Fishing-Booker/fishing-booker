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

    @Column(name = "max_persons")
    private Integer maxPersons;

    public Adventure() {
    }

    public Adventure(Integer id, User owner, String name, Location location, String description, String rules,
                     String cancelConditions, double averageGrade, String biography, Integer maxPersons, List<Image> images, List<Bedroom> bedrooms) {
        super(id, owner, name, location, description, rules, cancelConditions, averageGrade, images, bedrooms);
        this.biography = biography;
        this.maxPersons = maxPersons;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }
}
