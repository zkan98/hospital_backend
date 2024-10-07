package com.example.hospital_backend.photo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PhotoDTO {
    private Long id;

    @NotBlank(message = "URL은 필수입니다.")
    @Size(max = 255, message = "URL은 최대 255자까지 입력할 수 있습니다.")
    @Pattern(regexp = "^(http|https)://.*$", message = "URL은 http 또는 https로 시작해야 합니다.")
    private String url;
}
