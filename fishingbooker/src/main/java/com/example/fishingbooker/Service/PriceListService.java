package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.PriceDTO;
import com.example.fishingbooker.DTO.ReservationEntityDTO;
import com.example.fishingbooker.Enum.ServiceType;
import com.example.fishingbooker.IRepository.IPriceListRepository;
import com.example.fishingbooker.IRepository.IReservationEntityRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.IPriceListService;
import com.example.fishingbooker.Model.Lodge;
import com.example.fishingbooker.Model.PriceList;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PriceListService implements IPriceListService {

    @Autowired
    private IPriceListRepository repository;

    @Autowired
    private IReservationEntityRepository entityRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<PriceDTO> findEntityPrices(Integer entityId) {
        List<PriceDTO> prices = new ArrayList<>();
        for (PriceList p : repository.findEntityPrices(entityId)) {
            prices.add(new PriceDTO(p.getServiceName(), p.getServicePrice(), p.getServiceType().name(), entityId));
        }
        return prices;
    }

    @Override
    public void savePrice(PriceDTO dto) {
        PriceList price = new PriceList();
        price.setServiceName(dto.getName());
        price.setServicePrice(dto.getPrice());
        price.setDeleted(false);
        if(dto.getServiceType().equals("Regular service")){
            price.setServiceType(ServiceType.regularService);
        } else {
            price.setServiceType(ServiceType.additionalService);
        }
        price.setReservationEntity(setEntity(dto.getEntityId(), 1));
        repository.save(price);
    }

    private ReservationEntity setEntity(Integer entityId, Integer ownerId){
        User owner = userRepository.getById(ownerId);
        ReservationEntity entity = entityRepository.findEntityById(entityId);
        entity.setOwner(owner);
        return entity;
    }
}
