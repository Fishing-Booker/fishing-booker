package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IImageRepository extends JpaRepository<Image, Integer> {
    Image save(Image image);

    @Query("select i from Image i where i.reservationEntity.id=?1 and i.isDeleted=false")
    List<Image> findEnitityImages(Integer entityId);

    @Query("update Image i set i.isDeleted=true where i.id=?1")
    @Modifying
    @Transactional
    void deleteImage(Integer imageId);
}
