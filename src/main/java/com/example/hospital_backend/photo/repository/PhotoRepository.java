package com.example.hospital_backend.photo.repository;

import com.example.hospital_backend.photo.entity.Photo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    // 특정 리뷰에 첨부된 사진 조회
    List<Photo> findByReviewId(Long reviewId);

}
