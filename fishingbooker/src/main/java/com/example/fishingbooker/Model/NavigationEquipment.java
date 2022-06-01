package com.example.fishingbooker.Model;

import javax.persistence.*;

@Entity
@Table(name = "navigationEquipemnt")
public class NavigationEquipment {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "navigationEquipmentSeqGen", sequenceName = "navigationEquipmentSeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "navigationEquipmentSeqGen")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Ship.class, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    //@JoinColumn(name = "ship_id", referencedColumnName = "entity_id")
    private Ship ship;

    @Column(name = "name")
    private String name;

    public NavigationEquipment() {
    }

    public NavigationEquipment(Integer id, Ship ship, String name) {
        this.id = id;
        this.ship = ship;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
