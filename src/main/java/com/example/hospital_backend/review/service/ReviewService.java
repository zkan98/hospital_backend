package com.example.hospital_backend.review.service;

import com.example.hospital_backend.User.entity.User;
import com.example.hospital_backend.User.repository.UserRepository;
import com.example.hospital_backend.exception.InvalidReviewException;
import com.example.hospital_backend.hospital.entity.Hospital;
import com.example.hospital_backend.hospital.repository.HospitalRepository;
import com.example.hospital_backend.review.dto.ReviewDTO;
import com.example.hospital_backend.review.entity.Review;
import com.example.hospital_backend.review.mapper.ReviewMapper;
import com.example.hospital_backend.review.repository.ReviewRepository;
import com.example.hospital_backend.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;
    private final ReviewMapper reviewMapper;

    public List<ReviewDTO> findReviewsByHospitalId(Long hospitalId) {
        // 병원 존재 여부 확인
        Hospital hospital = hospitalRepository.findById(hospitalId)
            .orElseThrow(() -> new ResourceNotFoundException("Hospital not found with id: " + hospitalId));

        return reviewRepository.findByHospitalId(hospitalId).stream()
            .map(reviewMapper::toReviewDTO)
            .collect(Collectors.toList());
    }

    public List<ReviewDTO> findReviewsByUserId(Long userId) {
        // 사용자 존재 여부 확인
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return reviewRepository.findByUserId(userId).stream()
            .map(reviewMapper::toReviewDTO)
            .collect(Collectors.toList());
    }

    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        // 별점 유효성 검사 (1-5)
        if (reviewDTO.getRating() < 1 || reviewDTO.getRating() > 5) {
            throw new InvalidReviewException("Rating must be between 1 and 5.");
        }

        // 병원 존재 여부 확인
        Hospital hospital = hospitalRepository.findById(reviewDTO.getHospitalId())
            .orElseThrow(() -> new ResourceNotFoundException("Hospital not found with id: " + reviewDTO.getHospitalId()));

        // 사용자 존재 여부 확인
        User user = userRepository.findById(reviewDTO.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + reviewDTO.getUserId()));

        // 리뷰 생성
        Review review = reviewMapper.toReview(reviewDTO);
        review.setHospital(hospital);
        review.setUser(user);

        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toReviewDTO(savedReview);
    }

    // 리뷰가 존재하는지 확인하는 메서드 (Repository에서 호출)
    public boolean existsById(Long reviewId) {
        return reviewRepository.existsById(reviewId);
    }
}
