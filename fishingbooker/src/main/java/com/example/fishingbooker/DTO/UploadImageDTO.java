package com.example.fishingbooker.DTO;

public class UploadImageDTO {

    private Integer owner;
    private String base64;
    private Integer entityId;

    public UploadImageDTO() {
    }

    public UploadImageDTO(Integer owner, String base64, Integer entityId) {
        this.owner = owner;
        this.base64 = base64;
        this.entityId = entityId;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

}
