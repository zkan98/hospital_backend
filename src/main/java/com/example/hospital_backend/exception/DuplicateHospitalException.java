package com.example.hospital_backend.exception;

public class DuplicateHospitalException extends RuntimeException {
    public DuplicateHospitalException(String message) {
        super(message);
    }
}
