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
            String base64 = encodeImageToBase64(i.getPath());
            ImageDTO dto = new ImageDTO(i.getId(), base64);
            imageDTOS.add(dto);
        }
        return imageDTOS;
    }

    @Override
    public void deleteImage(Integer imageId) {
        imageRepository.deleteImage(imageId);
    }

    public void saveImage(UploadImageDTO uploadImageDTO) throws IOException {
        ReservationEntity reservationEntity = setEntity(uploadImageDTO.getEntityId(), 1);
        String basePath = new File("images/").getAbsolutePath();
        String path = basePath + "/" + reservationEntity.getName() + setId() + ".jpg";
        decodeImageFromBase64(uploadImageDTO.getBase64(), path);
        String relativePath = "/images/" + reservationEntity.getName() + setId() + ".jpg";

        reservationEntity.setOwner(userService.findUserById(11));
        Image image = new Image(setId(), reservationEntity, relativePath);

        save(image);
    }

   private void decodeImageFromBase64(String base64String, String imagePath) throws IOException {
        String part[] = base64String.split(",");
        byte[] data = Base64.getDecoder().decode(part[1]);

        try (OutputStream stream = new FileOutputStream(imagePath)) {
            stream.write(data);
        }
    }

    public String encodeImageToBase64(String relativePath) throws IOException {
        String path = getImagePath(relativePath);
        File file = new File(path);
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            String base64String= new String(Base64.getEncoder().encode(bytes), "UTF-8");
            String header = "data:image/jpeg;base64,";
            return header + base64String;
        } catch (IOException e) { e.printStackTrace(); }

        return "";
    }

    private String getImagePath(String relativePath) {
        String basePath = new File("images/").getAbsolutePath();
        String[] paths = relativePath.split("/"); //path is like /images/cottageNameIndex.jpg
        StringBuilder path = new StringBuilder().append(basePath).append("/").append(paths[2]);
        return path.toString();
    }

    private ReservationEntity setEntity(Integer entityId, Integer ownerId){
        User owner = userRepository.getById(ownerId);
        ReservationEntity entity = entityRepository.findEntityById(entityId);
        entity.setOwner(owner);
        return entity;
    }

}
