package com.example.hospital_backend.photo.service;

import com.example.hospital_backend.exception.BadRequestException;
import com.example.hospital_backend.exception.ResourceNotFoundException;
import com.example.hospital_backend.photo.dto.PhotoDTO;
import com.example.hospital_backend.photo.entity.Photo;
import com.example.hospital_backend.photo.mapper.PhotoMapper;
import com.example.hospital_backend.photo.repository.PhotoRepository;
import com.example.hospital_backend.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final PhotoMapper photoMapper;
    private final ReviewService reviewService;
    public List<PhotoDTO> findPhotosByReviewId(Long reviewId) {
        // 리뷰가 존재하는지 확인
        if (!reviewService.existsById(reviewId)) {
            throw new ResourceNotFoundException("Review not found with ID: " + reviewId);
        }
        return photoRepository.findByReviewId(reviewId).stream()
            .map(photoMapper::toPhotoDTO)
            .collect(Collectors.toList());
    }

    public PhotoDTO savePhoto(PhotoDTO photoDTO) {
        // URL 유효성 검사는 DTO에서 이미 처리됨

        if (photoDTO.getUrl() == null || photoDTO.getUrl().isEmpty()) {
            throw new BadRequestException("Photo URL cannot be empty.");
        }

        Photo photo = photoMapper.toPhoto(photoDTO);
        Photo savedPhoto = photoRepository.save(photo);
        return photoMapper.toPhotoDTO(savedPhoto);
    }
}
