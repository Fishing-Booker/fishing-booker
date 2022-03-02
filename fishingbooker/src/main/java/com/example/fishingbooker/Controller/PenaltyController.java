package com.example.fishingbooker.Controller;

import com.example.fishingbooker.IService.IPenaltyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/penalties", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class PenaltyController {

    @Autowired
    private IPenaltyService penaltyService;

    @GetMapping()
    public Integer getClientPenalties(@RequestParam Integer clientId) {
        return penaltyService.findClientPenalties(clientId);
    }
}
