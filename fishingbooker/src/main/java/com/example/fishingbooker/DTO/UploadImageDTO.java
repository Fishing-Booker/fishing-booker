package com.example.fishingbooker.DTO;

public class UploadImageDTO {
    private String base64;
    private Integer entityId;

    public UploadImageDTO() {
    }

    public UploadImageDTO(String base64, Integer entityId) {
        this.base64 = base64;
        this.entityId = entityId;
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
