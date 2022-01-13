package com.example.fishingbooker.DTO.priceList;

public class LoadPriceDTO {
    private String service_name;
    private double price;

    public LoadPriceDTO() {
    }

    public LoadPriceDTO(String service_name, double price) {
        this.service_name = service_name;
        this.price = price;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
