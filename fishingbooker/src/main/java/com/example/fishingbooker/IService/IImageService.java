package com.example.fishingbooker.IService;

import com.example.fishingbooker.DTO.ImageDTO;
import com.example.fishingbooker.Model.Image;

import java.io.IOException;
import java.util.List;

public interface IImageService {
    void save(Image image);

    Integer setId();

    List<ImageDTO> findEntityImages(Integer entityId) throws IOException;

    void deleteImage(Integer imageId);
}
