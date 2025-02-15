package com.example.crud.user.controller;

import com.example.crud.user.dto.UserProfileRequestDto;
import com.example.crud.user.model.UserProfile;
import com.example.crud.user.service.UserProfileService;
import com.example.crud.utils.ApiResponse;
import com.example.crud.utils.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserProfileController {

    private final UserProfileService userProfileService;

    // 새로운 데이터를 생성하려면 post방식! (id는 테이블생성시 자동생성되도록 만들었음)
    // 사용자 생성
    @PostMapping("/new")
    public ApiResponse<?> createUserProfile (@RequestBody UserProfileRequestDto requestDto){
        userProfileService.createUserProfile(requestDto);
        return ApiResponse.success(SuccessCode.INSERT_SUCCESS);
    }


    // 수정하려면 put방식!
    // 시용자 수정
    @PutMapping("/{uuid}")
    public ApiResponse<?> updateUserProfile (@PathVariable("uuid") UUID uuid, @RequestBody UserProfileRequestDto requestDto){
        userProfileService.updateUserProfile(uuid, requestDto);
        return ApiResponse.success(SuccessCode.UPDATE_SUCCESS);
    }

    // 삭제하려면 delete방식!
    // 사용자 삭제
    @DeleteMapping("/{uuid}")
    public ApiResponse<?> deleteUserProfile(@PathVariable("uuid") UUID uuid){
        userProfileService.deleteUserProfile(uuid);
        return ApiResponse.success(SuccessCode.DELETE_SUCCESS);
    }


    // id를 인자로 받아서 해당 UserProfile의 정보를 json형태로 전달하는 API를 만들것임
    // 사용자 조회
    @GetMapping("/{uuid}")
    public ApiResponse<?> getUserProfile(@PathVariable("uuid") UUID uuid){
        UserProfile userProfile = userProfileService.getUserProfile(uuid);
        return ApiResponse.success(SuccessCode.ALL_ID_FIND_SUCCESS, userProfile);
    }


    // 리스트 전체를 호출하는 API 만들기
    // 전체 사용자 프로필 조회
    @GetMapping("/all")
    public ApiResponse<?> getUserProfileList(){
        List<UserProfile> userProfiles = userProfileService.getAllUserProfiles();
        return ApiResponse.success(SuccessCode.ALL_DATA_FIND_SUCCESS, userProfiles);
    }
}

