package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.priceList.AddPriceDTO;
import com.example.fishingbooker.DTO.priceList.PriceDTO;
import com.example.fishingbooker.Enum.ServiceType;
import com.example.fishingbooker.IRepository.IPriceListRepository;
import com.example.fishingbooker.IRepository.IReservationEntityRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.IPriceListService;
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
            String serviceType;
            if(p.getServiceType() == ServiceType.regularService){
                serviceType = "Regular service";
            } else {
                serviceType = "Additional service";
            }
            prices.add(new PriceDTO(p.getId(), p.getServiceName(), p.getServicePrice(), serviceType, entityId));
        }
        return prices;
    }

    @Override
    public void savePrice(AddPriceDTO dto) {
        PriceList price = new PriceList();
        price.setServiceName(dto.getName());
        price.setServicePrice(dto.getPrice());
        price.setDeleted(false);
        if(dto.getServiceType().equals("Regular service")){
            price.setServiceType(ServiceType.regularService);
        } else {
            price.setServiceType(ServiceType.additionalService);
        }
        price.setReservationEntity(setEntity(dto.getEntityId(), dto.getOwner()));
        repository.save(price);
    }

    @Override
    public void updatePrice(PriceDTO price) {
        ServiceType serviceType;
        if(price.getServiceType().equals("Regular service")){
            serviceType = ServiceType.regularService;
        } else {
            serviceType = ServiceType.additionalService;
        }
        repository.updatePrice(price.getName(), price.getPrice(), serviceType, price.getId());
    }

    @Override
    public PriceDTO findEntityPrice(Integer entityId, Integer priceId) {
        PriceList price = repository.findEntityPrice(entityId, priceId);
        String serviceType;
        if(price.getServiceType() == ServiceType.regularService){
            serviceType = "Regular service";
        } else {
            serviceType = "Additional service";
        }
        return new PriceDTO(priceId, price.getServiceName(), price.getServicePrice(), serviceType, entityId);
    }

    @Override
    public void deletePrice(Integer priceId) {
        repository.deletePrice(priceId);
    }

    private ReservationEntity setEntity(Integer entityId, Integer ownerId){
        User owner = userRepository.getById(ownerId);
        ReservationEntity entity = entityRepository.findEntityById(entityId);
        entity.setOwner(owner);
        return entity;
    }
}
