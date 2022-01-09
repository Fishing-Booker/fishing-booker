package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.ImageDTO;
import com.example.fishingbooker.DTO.UploadImageDTO;
import com.example.fishingbooker.IService.IImageService;
import com.example.fishingbooker.IService.IReservationEntityService;
import com.example.fishingbooker.Model.Image;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Service.ImageService;
import com.example.fishingbooker.config.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping(value = "/images", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin

@Slf4j
public class ImageController {

    @Autowired
    private IImageService imageService;

    @Autowired
    private ImageService service;

    @Autowired
    private IReservationEntityService reservationEntityService;

    @PostMapping("/uploadImage")
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<Image> uploadImage(@RequestBody UploadImageDTO uploadImageDTO) throws IOException {
        service.saveImage(uploadImageDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getImages/{entityId}")
    public List<ImageDTO> getEntityImages(@PathVariable Integer entityId) throws IOException {
        return imageService.findEntityImages(entityId);
    }

    @DeleteMapping("/deleteImage/{imageId}")
    public ResponseEntity<Image> deleteImage(@PathVariable Integer imageId) {
        imageService.deleteImage(imageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
