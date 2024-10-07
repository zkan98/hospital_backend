package com.example.hospital_backend.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class ReviewDTO {
    private Long id;

    @NotBlank(message = "Username은 필수입니다.")
    @Size(max = 50, message = "Username은 최대 50자까지 입력할 수 있습니다.")
    private String username; // 리뷰 작성자

    @NotBlank(message = "Hospital Name은 필수입니다.")
    @Size(max = 100, message = "Hospital Name은 최대 100자까지 입력할 수 있습니다.")
    private String hospitalName; // 병원 이름

    @Min(value = 1, message = "Rating은 최소 1 이상이어야 합니다.")
    @Max(value = 5, message = "Rating은 최대 5 이하여야 합니다.")
    private int rating; // 별점 (1-5)

    @NotBlank(message = "Content는 필수입니다.")
    @Size(max = 1000, message = "Content는 최대 1000자까지 입력할 수 있습니다.")
    private String content; // 리뷰 내용

    @NotNull(message = "Hospital ID는 필수입니다.")
    private Long hospitalId;

    @NotNull(message = "User ID는 필수입니다.")
    private Long userId;

    private Date createdAt; // 생성일자는 서버에서 자동 설정할 수 있습니다.
}
