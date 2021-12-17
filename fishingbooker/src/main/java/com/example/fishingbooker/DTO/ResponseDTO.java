package com.example.fishingbooker.DTO;

public class ResponseDTO {
    private Integer requestId;
    private String userUsername;
    private String response;

    ResponseDTO() {}

    public ResponseDTO(Integer requestId, String userUsername, String response) {
        this.requestId = requestId;
        this.userUsername = userUsername;
        this.response = response;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
