package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.ImageDTO;
import com.example.fishingbooker.DTO.UploadImageDTO;
import com.example.fishingbooker.IRepository.IImageRepository;
import com.example.fishingbooker.IRepository.IReservationEntityRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.IImageService;
import com.example.fishingbooker.IService.IReservationEntityService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Model.Image;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ImageService implements IImageService {
    @Autowired
    private IImageRepository imageRepository;

    @Autowired
    private IReservationEntityService reservationEntityService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IReservationEntityRepository entityRepository;

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }

    @Override
    public Integer setId() {
        List<Image> images = imageRepository.findAll();
        if(images.size() != 0) {
            Image image = images.get(images.size() - 1);
            return image.getId() + 1;
        } else {
            return 1;
        }
    }

    @Override
    public List<ImageDTO> findEntityImages(Integer entityId) throws IOException {
        List<Image> images = imageRepository.findEnitityImages(entityId);
        List<ImageDTO> imageDTOS = new ArrayList<>();
        for (Image i: images) {
            ImageDTO dto = new ImageDTO(i.getId(), i.getBase64());
            imageDTOS.add(dto);
        }
        return imageDTOS;
    }

    @Override
    public String getEntityProfileImage(Integer entityId) throws IOException {
        List<Image> images = imageRepository.findEnitityImages(entityId);
        if(images.isEmpty()) return "";
        return images.get(0).getBase64();
    }

    @Override
    public void deleteImage(Integer imageId) {
        imageRepository.deleteImage(imageId);
    }

    public void saveImage(UploadImageDTO uploadImageDTO){
        ReservationEntity reservationEntity = setEntity(uploadImageDTO.getEntityId(), uploadImageDTO.getOwner());
        reservationEntity.setOwner(userService.findUserById(uploadImageDTO.getOwner()));
        Image image = new Image();
        image.setReservationEntity(reservationEntity);
        image.setBase64(uploadImageDTO.getBase64());
        image.setDeleted(false);
        save(image);
    }

    private ReservationEntity setEntity(Integer entityId, Integer ownerId){
        User owner = userRepository.getById(ownerId);
        ReservationEntity entity = entityRepository.findEntityById(entityId);
        entity.setOwner(owner);
        return entity;
    }

}
