package com.example.fishingbooker.Controller;

import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Service.ReservationEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entities")
@CrossOrigin
public class ReservationEntityController {

    @Autowired
    private ReservationEntityService entityService;

    @GetMapping("/allReservationEntities")
    public List<ReservationEntity> getEntities(){
        return entityService.getEntities();
    }

    @GetMapping("/lodges")
    public List<ReservationEntity> getLodges() {
        return entityService.getLodges();
    }

    @PostMapping("/add")
    public ReservationEntity addEntity(ReservationEntity entity){
        return entityService.addEntity(entity);
    }

}
