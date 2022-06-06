package com.example.fishingbooker.Model;

import com.example.fishingbooker.Enum.CategoryType;

import javax.persistence.*;

@Entity
@Table(name="user_category")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserCategory {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "userCategorySeqGen", sequenceName = "userCategorySeqGen", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userCategorySeqGen")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    //@JoinColumn(name="client_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "category_type")
    private CategoryType categoryType;

    @Column(name = "user_points")
    private double points;

    public UserCategory() { }

    public UserCategory(Integer id, User client, CategoryType categoryType, double points) {
        this.id = id;
        this.user = client;
        this.categoryType = categoryType;
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User client) {
        this.user = client;
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
