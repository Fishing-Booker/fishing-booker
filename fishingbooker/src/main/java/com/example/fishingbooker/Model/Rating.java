package com.example.fishingbooker.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    private int id;
    private int entityId;
    private int grade;
    private String comment;
    private boolean isApproved;

    public Rating() {
    }

    public Rating(int id, int entityId, int grade, String comment) {
        this.id = id;
        this.entityId = entityId;
        this.grade = grade;
        this.comment = comment;
        this.isApproved = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
