package com.example.fishingbooker.DTO;

public class ImageDTO {
    private Integer imageId;
    private String base64;

    public ImageDTO() {
    }

    public ImageDTO(Integer imageId, String base64) {
        this.imageId = imageId;
        this.base64 = base64;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
