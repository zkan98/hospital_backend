package com.example.hospital_backend.favorite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FavoriteDTO {

    private Long id;

    @NotBlank(message = "Username은 필수입니다.")
    @Size(max = 50, message = "Username은 최대 50자까지 입력할 수 있습니다.")
    private String username;  // 사용자 이름

    @NotBlank(message = "Hospital Name은 필수입니다.")
    @Size(max = 100, message = "Hospital Name은 최대 100자까지 입력할 수 있습니다.")
    private String hospitalName;  // 병원 이름
}
