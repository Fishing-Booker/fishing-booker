package com.example.fishingbooker.DTO.priceList;

public class PriceDTO {

    private Integer id;
    private String name;
    private double price;
    private String serviceType;
    private Integer entityId;

    public PriceDTO() {
    }

    public PriceDTO(Integer id, String name, double price, String serviceType, Integer entityId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.serviceType = serviceType;
        this.entityId = entityId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }
}
