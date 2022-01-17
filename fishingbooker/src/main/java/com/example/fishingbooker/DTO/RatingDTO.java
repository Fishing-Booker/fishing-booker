package com.example.fishingbooker.DTO;

public class RatingDTO {
    private Integer id;
    private String comment;
    private Integer grade;
    private static boolean isApproved = false;
    private static boolean isDisapproved = false;
    private Integer entityId;
    private Integer clientId;

    public RatingDTO() {
    }

    public RatingDTO(Integer id, String comment, Integer grade, Integer entityId, Integer clientId) {
        this.id = id;
        this.comment = comment;
        this.grade = grade;
        this.entityId = entityId;
        this.clientId = clientId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public static boolean isIsApproved() {
        return isApproved;
    }

    public static void setIsApproved(boolean isApproved) {
        RatingDTO.isApproved = isApproved;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public static boolean isIsDisapproved() {
        return isDisapproved;
    }

    public static void setIsDisapproved(boolean isDisapproved) {
        RatingDTO.isDisapproved = isDisapproved;
    }
}
