package com.example.hospital_backend.favorite.mapper;

import com.example.hospital_backend.favorite.dto.FavoriteDTO;
import com.example.hospital_backend.favorite.entity.Favorite;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FavoriteMapper {
    FavoriteDTO toFavoriteDTO(Favorite favorite);
    Favorite toFavorite(FavoriteDTO favoriteDTO);
}
