package com.example.crud.user.repository;

import com.example.crud.user.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
//JPA를 사용하여 데이터베이스와 상호작용
public interface UserProfileRepository extends JpaRepository<UserProfile, UUID>{
}
