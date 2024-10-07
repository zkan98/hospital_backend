package com.example.hospital_backend.hospital.entity;

import com.example.hospital_backend.favorite.entity.Favorite;
import com.example.hospital_backend.review.entity.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "hospitals")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phoneNumber;
    private Double latitude;
    private Double longitude;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @OneToMany(mappedBy = "hospital")
    private List<Review> reviews;

    @OneToMany(mappedBy = "hospital")
    private List<Favorite> favorites;

    @Transient
    private double rating; // 병원의 평균 평점(계산용)
}

