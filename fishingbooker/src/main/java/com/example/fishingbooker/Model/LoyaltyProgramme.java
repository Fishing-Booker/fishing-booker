package com.example.fishingbooker.Model;

import javax.persistence.*;

@Entity
@Table(name = "loyalty_programme")
@Inheritance(strategy = InheritanceType.JOINED)
public class LoyaltyProgramme {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "owner_income")
    private double ownerIncome;

    @Column(name = "client_income")
    private double clientIncome;

    @Column(name = "bronze_limit")
    private double bronzeLimit;

    @Column(name = "silver_limit")
    private double silverLimit;

    @Column(name = "gold_limit")
    private double goldLimit;

    public LoyaltyProgramme() {
    }


    public LoyaltyProgramme(Integer id, double ownerIncome, double clientIncome, double bronzeLimit, double silverLimit, double goldLimit) {
        this.id = id;
        this.ownerIncome = ownerIncome;
        this.clientIncome = clientIncome;
        this.bronzeLimit = bronzeLimit;
        this.silverLimit = silverLimit;
        this.goldLimit = goldLimit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getOwnerIncome() {
        return ownerIncome;
    }

    public void setOwnerIncome(double ownerIncome) {
        this.ownerIncome = ownerIncome;
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
