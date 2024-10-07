package com.example.hospital_backend.favorite.repository;

import com.example.hospital_backend.favorite.entity.Favorite;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    // 특정 사용자의 즐겨찾기 목록 조회
    List<Favorite> findByUserId(Long userId);
    boolean existsByUserUsernameAndHospitalName(String username, String hospitalName);

}