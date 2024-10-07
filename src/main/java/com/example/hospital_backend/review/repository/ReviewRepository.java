package com.example.hospital_backend.review.repository;

import com.example.hospital_backend.review.entity.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 특정 병원에 대한 리뷰 목록 조회
    List<Review> findByHospitalId(Long hospitalId);

    // 특정 사용자가 작성한 리뷰 목록 조회
    List<Review> findByUserId(Long userId);


}
