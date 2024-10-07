//package com.example.hospital_backend.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable())  // CSRF 보호 비활성화
//            .authorizeHttpRequests(auth -> auth
//                .anyRequest().permitAll()  // 모든 요청 허용
//            )
//            .httpBasic().disable();  // 기본 인증 비활성화
//        return http.build();
//    }
//}
