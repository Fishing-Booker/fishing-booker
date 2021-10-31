package com.example.fishingbooker.Model;

import com.example.fishingbooker.Enum.ReservationType;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "reservation")
public class Reservation extends Appointment {

    private String clientUsername;
    private ReservationType reservationType;

    public Reservation() {
    }

    public Reservation(int id, int entityId, Date startDate, Date endDate, boolean isBooked, int maxPersons, double price, String clientUsername, ReservationType reservationType) {
        super(id, entityId, startDate, endDate, isBooked, maxPersons, price);
        this.clientUsername = clientUsername;
        this.reservationType = reservationType;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public ReservationType getReservationType() {
        return reservationType;
    }

    public void setReservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
    }
}
