package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.PriceDTO;
import com.example.fishingbooker.Model.PriceList;

import java.util.List;

public interface IPriceListService {

    List<PriceDTO> findEntityPrices(Integer entityId);

    void savePrice(PriceDTO dto);

}
