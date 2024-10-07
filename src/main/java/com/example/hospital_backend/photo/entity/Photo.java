package com.example.hospital_backend.photo.entity;

import com.example.hospital_backend.review.entity.Review;
import jakarta.persistence.*;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    private String url; // 사진 파일의 경로 또는 URL
    private Date uploadedAt;
}
