package com.example.fishingbooker.Model;

import com.example.fishingbooker.Enum.ServiceType;

import javax.persistence.*;

@Entity
@Table(name = "pricelist")
public class Pricelist {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private int entityId;
    private int reservationId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_price")
    private double servicePrice;

    @Column(name = "service_type")
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    public Pricelist() {
    }

    public Pricelist(Integer id, int entityId, int reservationId, String serviceName, double servicePrice, ServiceType serviceType) {
        this.id = id;
        this.entityId = entityId;
        this.reservationId = reservationId;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.serviceType = serviceType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
}
