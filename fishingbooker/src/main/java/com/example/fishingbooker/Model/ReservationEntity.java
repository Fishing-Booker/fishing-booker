package com.example.fishingbooker.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reservation_entity")
@Inheritance(strategy = InheritanceType.JOINED)
public class ReservationEntity {

    @Id
    @Column(name = "entity_id", nullable = false)
    @SequenceGenerator(name = "reservationEntitySeqGen", sequenceName = "reservationEntitySeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservationEntitySeqGen")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.MERGE})
    //@JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    private User owner;

    @Column(name = "name")
    private String name;

    @OneToOne(targetEntity = Location.class, optional = false, cascade = {CascadeType.MERGE})
    //@JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @Column(name = "description")
    private String description;

    @Column(name = "rules")
    private String rules;

    @Column(name = "cancel_conditions")
    private double cancelConditions;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "average_grade")
    private double averageGrade;

    @Column(name = "max_persons")
    private Integer maxPersons;

    public ReservationEntity() { }

    public ReservationEntity(Integer id, User owner, String name, Location location, String description, String rules,
                             double cancelConditions, double averageGrade, Integer maxPersons) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.location = location;
        this.description = description;
        this.rules = rules;
        this.cancelConditions = cancelConditions;
        this.isDeleted = false;
        this.averageGrade = averageGrade;
        this.maxPersons = maxPersons;
    }

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public double getCancelConditions() {
        return cancelConditions;
    }

    public void setCancelConditions(double cancelConditions) {
        this.cancelConditions = cancelConditions;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }
}
