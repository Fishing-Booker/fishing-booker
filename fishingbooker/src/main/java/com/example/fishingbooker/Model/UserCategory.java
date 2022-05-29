package com.example.fishingbooker.Model;

import com.example.fishingbooker.Enum.CategoryType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user_category")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserCategory {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    //@JoinColumn(name="client_id", referencedColumnName = "user_id")
    private User client;

    @Column(name = "category_type")
    private CategoryType categoryType;

    @Column(name = "user_points")
    private double points;

    public UserCategory() { }

    public UserCategory(Integer id, User client, CategoryType categoryType, double points) {
        this.id = id;
        this.client = client;
        this.categoryType = categoryType;
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }
}
