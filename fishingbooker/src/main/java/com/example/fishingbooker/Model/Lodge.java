package com.example.fishingbooker.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lodge")
@Inheritance(strategy = InheritanceType.JOINED)
public class Lodge  extends ReservationEntity{

    @OneToMany(mappedBy = "lodge", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Bedroom> bedrooms = new ArrayList<>();

    public Lodge() {
    }

    public Lodge(Integer id, User owner, String name, Location location, String description, String rules,
                 double cancelConditions, double averageGrade, Integer maxPersons, List<Image> images, List<Bedroom> bedrooms) {
        super(id, owner, name, location, description, rules, cancelConditions, averageGrade, maxPersons, images);
        this.bedrooms = bedrooms;
    }

    public Lodge(Integer id, User owner, String name, Location location, String description, String rules,
                 double cancelConditions, double averageGrade, Integer maxPersons, List<Image> images) {
        super(id, owner, name, location, description, rules, cancelConditions, averageGrade, maxPersons, images);
    }

    public List<Bedroom> getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(List<Bedroom> bedrooms) {
        this.bedrooms = bedrooms;
    }
}
