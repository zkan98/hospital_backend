package com.example.hospital_backend.photo.mapper;

import com.example.hospital_backend.photo.dto.PhotoDTO;
import com.example.hospital_backend.photo.entity.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhotoMapper {
    PhotoDTO toPhotoDTO(Photo photo);
    Photo toPhoto(PhotoDTO photoDTO);
}
