package com.example.fishingbooker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComplaintDTO {
    private Integer clientId;
    private Integer entityId;
    private String text;
}
