package com.example.fishingbooker.Model;

import com.example.fishingbooker.Enum.ServiceType;

import javax.persistence.*;

@Entity
@Table(name = "price_list")
public class PriceList {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "entity_id", referencedColumnName = "entity_id")
    private ReservationEntity reservationEntity;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_price")
    private double servicePrice;

    @Column(name = "service_type")
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public PriceList() {
    }

    public PriceList(Integer id, ReservationEntity reservationEntity, String serviceName, double servicePrice, ServiceType serviceType) {
        this.id = id;
        this.reservationEntity = reservationEntity;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.serviceType = serviceType;
        this.isDeleted = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReservationEntity getReservationEntity() {
        return reservationEntity;
    }

    public void setReservationEntity(ReservationEntity reservationEntity) {
        this.reservationEntity = reservationEntity;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
