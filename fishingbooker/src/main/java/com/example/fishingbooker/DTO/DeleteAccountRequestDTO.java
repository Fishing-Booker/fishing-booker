package com.example.fishingbooker.DTO;

public class DeleteAccountRequestDTO {
    private String reason;
    private Integer id;

    public DeleteAccountRequestDTO() {}

    public DeleteAccountRequestDTO(String reason, Integer id) {
        this.reason = reason;
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
