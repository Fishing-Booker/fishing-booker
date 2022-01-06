package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IImageRepository extends JpaRepository<Image, Integer> {
    Image save(Image image);

    @Query("select i from Image i where i.reservationEntity.id=?1")
    List<Image> findEnitityImages(Integer entityId);
}
