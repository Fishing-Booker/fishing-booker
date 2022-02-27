package com.example.fishingbooker.DTO.loyaltyProgramme;

public class LoyaltyProgrammeDTO {
    private double ownerIncome;
    private double clientIncome;
    private double bronzeLimit;
    private double silverLimit;
    private double goldLimit;

    public LoyaltyProgrammeDTO() {
    }

    public LoyaltyProgrammeDTO(double systemIncome, double clientIncome, double bronzeLimit, double silverLimit, double goldLimit) {
        this.ownerIncome = systemIncome;
        this.clientIncome = clientIncome;
        this.bronzeLimit = bronzeLimit;
        this.silverLimit = silverLimit;
        this.goldLimit = goldLimit;
    }

    public double getOwnerIncome() {
        return ownerIncome;
    }

    public void setOwnerIncome(double systemIncome) {
        this.ownerIncome = systemIncome;
    }

    public double getClientIncome() {
        return clientIncome;
    }

    public void setClientIncome(double clientIncome) {
        this.clientIncome = clientIncome;
    }

    public double getBronzeLimit() {
        return bronzeLimit;
    }

    public void setBronzeLimit(double bronzeLimit) {
        this.bronzeLimit = bronzeLimit;
    }

    public double getSilverLimit() {
        return silverLimit;
    }

    public void setSilverLimit(double silverLimit) {
        this.silverLimit = silverLimit;
    }

    public double getGoldLimit() {
        return goldLimit;
    }

    public void setGoldLimit(double goldLimit) {
        this.goldLimit = goldLimit;
    }
}
