package com.example.crud.auth.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "user")

// 데이터베이스와 연동되는 사용자 엔티티 클래스
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT -> DB에서 id값 자동 증가
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)    // 고유 이메일
    private String email;

    @Column(nullable = false)   // 암호화된 비밀번호
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;
}
