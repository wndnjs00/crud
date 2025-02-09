package com.example.crud.auth.repository;

import com.example.crud.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

// 데이터베이스와 상호작용하는 계층
// 사용자 정보를 DB에서 조회/저장/수정/삭제 (JpaRepository를 상속하여 CRUD 기능 자동제공)
// JpaRepository 상속 <객체, id타입>
@Repository
public interface AuthRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);   // 이메일로 사용자 검색(조회)
    boolean existsByEmail(String email);        // 이메일 중복 여부 확인
    Optional<User> findByRefreshToken(String refreshToken); // Refresh Token으로 사용자 검색
}
