package com.example.crud.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileRequestDto {
    private String name;
    private String phone;
    private String address;
    private String profileImage;
}
