package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.PriceDTO;
import com.example.fishingbooker.IService.IPriceListService;
import com.example.fishingbooker.Model.PriceList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/prices", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
@Slf4j
public class PriceListController {

    @Autowired
    private IPriceListService priceListService;

    @GetMapping("/entityPrices/{id}")
    public List<PriceDTO> getEntityPrices(@PathVariable Integer id){
        return priceListService.findEntityPrices(id);
    }

    @PostMapping("/addPrice")
    public ResponseEntity<String> addPrice(@RequestBody PriceDTO price){
        priceListService.savePrice(price);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
