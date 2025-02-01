package com.example.crud.auth.dto;

import com.example.crud.auth.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String email;
    private String name;
    private String phone;
    private String address;


    // info로 JWT 토큰을 검증할때 사용자 정보객체를 반환하기위해
    public static UserResponseDto fromEntity(User user) {

        // User엔티티를 UserResponseDto로 변환
        return UserResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
    }
}
