package com.example.hospital_backend.hospital.repository;

import com.example.hospital_backend.hospital.entity.Hospital;
import com.example.hospital_backend.hospital.entity.Specialty;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    // 진료 과목별 병원 리스트 조회
    List<Hospital> findBySpecialty(Specialty specialty);
    boolean existsByName(String name);
    Optional<Hospital> findByName(String name);
}
