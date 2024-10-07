package com.example.hospital_backend.favorite.controller;

import com.example.hospital_backend.favorite.dto.FavoriteDTO;
import com.example.hospital_backend.favorite.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavoriteDTO>> getFavoritesByUser(@PathVariable Long userId) {
        List<FavoriteDTO> favorites = favoriteService.findFavoritesByUserId(userId);
        return ResponseEntity.ok(favorites);
    }

    @PostMapping
    public ResponseEntity<FavoriteDTO> addFavorite(@Valid @RequestBody FavoriteDTO favoriteDTO) {
        FavoriteDTO savedFavorite = favoriteService.saveFavorite(favoriteDTO);
        return new ResponseEntity<>(savedFavorite, HttpStatus.CREATED);
    }
}
