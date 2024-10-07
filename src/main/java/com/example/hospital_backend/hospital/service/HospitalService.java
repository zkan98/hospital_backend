package com.example.hospital_backend.hospital.service;

import com.example.hospital_backend.exception.DuplicateHospitalException;
import com.example.hospital_backend.hospital.dto.HospitalDTO;
import com.example.hospital_backend.hospital.entity.Hospital;
import com.example.hospital_backend.hospital.entity.Specialty;
import com.example.hospital_backend.hospital.mapper.HospitalMapper;
import com.example.hospital_backend.hospital.repository.HospitalRepository;
import com.example.hospital_backend.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final HospitalMapper hospitalMapper;

    public List<HospitalDTO> findAllHospitals() {
        return hospitalRepository.findAll().stream()
            .map(hospitalMapper::toHospitalDTO)
            .collect(Collectors.toList());
    }

    public HospitalDTO findHospitalById(Long id) {
        return hospitalRepository.findById(id)
            .map(hospitalMapper::toHospitalDTO)
            .orElseThrow(() -> new ResourceNotFoundException("Hospital not found with id: " + id));
    }

    public List<HospitalDTO> findHospitalsBySpecialty(Specialty specialty) {
        return hospitalRepository.findBySpecialty(specialty).stream()
            .map(hospitalMapper::toHospitalDTO)
            .collect(Collectors.toList());
    }

    public HospitalDTO saveHospital(HospitalDTO hospitalDTO) {
        // 중복된 병원 이름 확인
        if (hospitalRepository.existsByName(hospitalDTO.getName())) {
            throw new DuplicateHospitalException("Hospital already exists with name: " + hospitalDTO.getName());
        }

        Hospital hospital = hospitalMapper.toHospital(hospitalDTO);
        Hospital savedHospital = hospitalRepository.save(hospital);
        return hospitalMapper.toHospitalDTO(savedHospital);
    }
}
