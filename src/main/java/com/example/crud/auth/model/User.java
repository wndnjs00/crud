package com.example.crud.auth.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(strategy = GenerationType.UUID) // UUID 값을 자동으로 생성 (Hibernate 5.3이상에서 권장)
    @Column(name = "uuid", nullable = false, updatable = false, columnDefinition = "BINARY(16)") // UUID를 BINARY(16)로 설정
    private UUID uuid;

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
