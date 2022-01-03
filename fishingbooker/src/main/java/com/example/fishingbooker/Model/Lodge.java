package com.example.fishingbooker.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lodge")
@Inheritance(strategy = InheritanceType.JOINED)
public class Lodge  extends ReservationEntity{

    @OneToMany(mappedBy = "lodge", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Bedroom> bedrooms;

    public Lodge() {
    }

    public Lodge(Integer id, User owner, String name, Location location, String description, String rules,
                 String cancelConditions, double averageGrade, List<Image> images, List<Bedroom> bedrooms) {
        super(id, owner, name, location, description, rules, cancelConditions, averageGrade, images);
        this.bedrooms = bedrooms;
    }

    public Lodge(Integer id, User owner, String name, Location location, String description, String rules,
                 String cancelConditions, double averageGrade, List<Image> images) {
        super(id, owner, name, location, description, rules, cancelConditions, averageGrade, images);
    }

    public List<Bedroom> getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(List<Bedroom> bedrooms) {
        this.bedrooms = bedrooms;
    }
}
