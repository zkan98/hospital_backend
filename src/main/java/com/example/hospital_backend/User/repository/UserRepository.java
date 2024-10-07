package com.example.hospital_backend.User.repository;

import com.example.hospital_backend.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 추가로 필요한 쿼리 메서드 선언 가능
    User findByUsername(String username);
    boolean existsByUsername(String username);


}
