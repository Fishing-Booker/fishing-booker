package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.priceList.AddPriceDTO;
import com.example.fishingbooker.DTO.priceList.LoadPriceDTO;
import com.example.fishingbooker.DTO.priceList.PriceDTO;

import java.util.List;

public interface IPriceListService {

    List<PriceDTO> findEntityPrices(Integer entityId);

    void savePrice(AddPriceDTO dto);

    void updatePrice(PriceDTO price);

    PriceDTO findEntityPrice(Integer entityId, Integer priceId);

    void deletePrice(Integer priceId);

    List<String> findAdditionalServices(Integer entityId);

    List<LoadPriceDTO> findRegularServices(Integer entityId);

    List<LoadPriceDTO> findAdditionalServices2(Integer entityId);

}
