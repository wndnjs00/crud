package com.example.crud.auth.repository;

import com.example.crud.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 사용자 정보를 DB에서 조회/저장/수정/삭제 (JpaRepository를 상속하여 CRUD 기능 자동제공)
public interface AuthRepository extends JpaRepository<User, Long> {

//    @Query("SELECT u FROM User u WHERE u.uuid_user=:uuid")
//    Optional<User> findByUuid(UUID uuid);
//    얘를 위에꺼처럼 findByUuid로 바꿔줘야함

    Optional<User> findByEmail(String email);   // 이메일로 사용자 검색

    boolean existsByEmail(String email);        // 이메일 중복 여부 확인
}
