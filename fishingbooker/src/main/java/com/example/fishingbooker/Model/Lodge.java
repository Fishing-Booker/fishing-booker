package com.example.fishingbooker.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "lodge")
public class Lodge extends ReservationEntity {

    @Column(name = "room_num")
    private Integer roomNumber;

    @Column(name = "bed_num")
    private Integer bedNumber;

    public Lodge() {
    }

    public Lodge(Integer id, User owner, String name, Location location, String description, String rules, String cancelConditions,
                 double averageGrade, Integer roomNumber, Integer bedNumber, List<Image> images) {
        super(id, owner, name, location, description, rules, cancelConditions, averageGrade, images);
        this.roomNumber = roomNumber;
        this.bedNumber = bedNumber;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(Integer bedNumber) {
        this.bedNumber = bedNumber;
    }
}
