package com.example.crud.user.service;

import com.example.crud.auth.model.User;
import com.example.crud.user.dto.UserProfileRequestDto;
import com.example.crud.user.model.UserProfile;
import com.example.crud.user.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    // requestDto를 요청해서, 사용자 생성
    public void createUserProfile(UserProfileRequestDto requestDto){
        UserProfile userProfile = UserProfile.builder()
                .name(requestDto.getName())
                .phone(requestDto.getPhone())
                .address(requestDto.getAddress())
                .profileImage(requestDto.getProfileImage()) // 이미지 데이터 추가
                .build();

        // userProfile 데이터를 db에 저장
        userProfileRepository.save(userProfile);
    }

    // 기존 사용자 프로필 수정
    public void updateUserProfile(UUID uuid, UserProfileRequestDto requestDto){
        UserProfile userProfile = getUserProfile(uuid);
        userProfile.setName(requestDto.getName());
        userProfile.setPhone(requestDto.getPhone());
        userProfile.setAddress(requestDto.getAddress());
        userProfile.setProfileImage(requestDto.getProfileImage());

        userProfileRepository.save(userProfile);
    }

    // 사용자 프로필 삭제
    public void deleteUserProfile(UUID uuid){
        UserProfile userProfile = getUserProfile(uuid);
        userProfileRepository.delete(userProfile);
    }


    // uuid로 특정 사용자 조회
    public UserProfile getUserProfile(UUID uuid){
        return userProfileRepository.findById(uuid)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 사용자입니다."));
    }

    // 전체 사용자 정보 조회
    public List<UserProfile> getAllUserProfiles(){
        return userProfileRepository.findAll();
    }

}
