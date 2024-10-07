package com.example.hospital_backend.photo.controller;

import com.example.hospital_backend.photo.dto.PhotoDTO;
import com.example.hospital_backend.photo.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/photos")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<List<PhotoDTO>> getPhotosByReview(@PathVariable Long reviewId) {
        List<PhotoDTO> photos = photoService.findPhotosByReviewId(reviewId);
        return ResponseEntity.ok(photos);
    }

    @PostMapping
    public ResponseEntity<PhotoDTO> uploadPhoto(@Valid @RequestBody PhotoDTO photoDTO) {
        PhotoDTO savedPhoto = photoService.savePhoto(photoDTO);
        return new ResponseEntity<>(savedPhoto, HttpStatus.CREATED);
    }
}
