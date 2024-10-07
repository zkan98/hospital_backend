package com.example.hospital_backend.favorite.service;

import com.example.hospital_backend.favorite.dto.FavoriteDTO;
import com.example.hospital_backend.favorite.entity.Favorite;
import com.example.hospital_backend.favorite.mapper.FavoriteMapper;
import com.example.hospital_backend.favorite.repository.FavoriteRepository;
import com.example.hospital_backend.exception.DuplicateFavoriteException;
import com.example.hospital_backend.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final FavoriteMapper favoriteMapper;

    public List<FavoriteDTO> findFavoritesByUserId(Long userId) {
        // 사용자 존재 여부 확인
        // UserService 또는 UserRepository를 통해 확인 가능
        // 여기서는 단순히 존재 여부만 확인한다고 가정
        // 실제로는 UserService를 주입받아 확인하는 것이 좋습니다.
        // 예시에서는 생략

        return favoriteRepository.findByUserId(userId).stream()
            .map(favoriteMapper::toFavoriteDTO)
            .collect(Collectors.toList());
    }

    public FavoriteDTO saveFavorite(FavoriteDTO favoriteDTO) {
        // 중복된 즐겨찾기 확인
        if (favoriteRepository.existsByUserUsernameAndHospitalName(favoriteDTO.getUsername(), favoriteDTO.getHospitalName())) {
            throw new DuplicateFavoriteException("Favorite already exists for user: " + favoriteDTO.getUsername() + " and hospital: " + favoriteDTO.getHospitalName());
        }

        Favorite favorite = favoriteMapper.toFavorite(favoriteDTO);
        Favorite savedFavorite = favoriteRepository.save(favorite);
        return favoriteMapper.toFavoriteDTO(savedFavorite);
    }
}
