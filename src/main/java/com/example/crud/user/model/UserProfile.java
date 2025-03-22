package com.example.crud.user.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "userprofile")    //데이터베이스의 UserProfile 테이블과 매핑
public class UserProfile {

    @Id     //pk
    @GeneratedValue(strategy = GenerationType.UUID) // UUID 값을 자동으로 생성
    @Column(name = "uuid", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Lob    //이미지 데이터를 저장할 큰 객체로 지정
    @Column(name = "profile_image", columnDefinition = "TEXT")
    private String profileImage;    // Base64로 인코딩된 이미지 데이터

}
