package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.priceList.AddPriceDTO;
import com.example.fishingbooker.DTO.priceList.PriceDTO;
import com.example.fishingbooker.IService.IPriceListService;
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
    public ResponseEntity<String> addPrice(@RequestBody AddPriceDTO price){
        priceListService.savePrice(price);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getEntityPrice/{id}/{priceId}")
    public PriceDTO getEntityPrice(@PathVariable Integer id, @PathVariable Integer priceId){
        return priceListService.findEntityPrice(id, priceId);
    }

    @PutMapping("/editPrice")
    public ResponseEntity<String> updatePrice(@RequestBody PriceDTO price){
        priceListService.updatePrice(price);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deletePrice/{id}")
    public ResponseEntity<String> deletePrice(@PathVariable Integer id){
        priceListService.deletePrice(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/additionalServices/{id}")
    public List<String> findAdditionalServices(@PathVariable Integer id){
        return priceListService.findAdditionalServices(id);
    }

}
