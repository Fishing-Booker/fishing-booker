package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.adventure.AdventureInfoDTO;
import com.example.fishingbooker.IService.IAdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/adventures", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class AdventureController {

    @Autowired
    private IAdventureService adventureService;

    @GetMapping()
    List<AdventureInfoDTO> getAll() {
        return adventureService.getAll();
    }

    @GetMapping("/letters")
    List<String> getFirstLetters() {
        return adventureService.getFirstLetters();
    }

    @GetMapping("/search")
    public List<AdventureInfoDTO> getSearchResults(@RequestParam(required = false) String name, @RequestParam(required = false) String letter) {
        return adventureService.searchAndFilter(name, letter);
    }
}
