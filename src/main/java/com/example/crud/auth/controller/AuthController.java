package com.example.crud.auth.controller;

import com.example.crud.auth.dto.LoginRequestDto;
import com.example.crud.auth.dto.RegisterRequestDto;
import com.example.crud.auth.dto.UserResponseDto;
import com.example.crud.auth.service.AuthService;
import com.example.crud.util.JwtUtil;
import com.example.crud.utils.ApiResponse;
import com.example.crud.utils.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


// 사용자 요청을 처리하고 응답을 반환하는 컨트롤러
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    // 회원가입
    @PostMapping("/register")
    public ApiResponse<?> registerUser(@RequestBody RegisterRequestDto registerRequestDto){

        authService.registerUser(registerRequestDto); // 회원가입 처리
        return ApiResponse.success(SuccessCode.SIGNUP_SUCCESS); //성공시 응답반환
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> loginUser(@RequestBody LoginRequestDto loginRequestDto) {

        Map<String, Object> response = authService.loginUser(loginRequestDto);// 로그인 처리
        return ApiResponse.success(SuccessCode.LOGIN_SUCCESS, response); // 성공응답과 토큰반환
    }


    // 사용자 정보 조회 API (토큰 검증을 통한)
    // JWT 토큰을 검증하고 사용자 정보를 반환
    @GetMapping("/info")
    public ResponseEntity<UserResponseDto> getUserInfo(@RequestHeader("Authorization") String authorizationHeader) {
        // Bearer 제거 후 순수 토큰 추출
        String token = jwtUtil.resolveToken(authorizationHeader);

        // Bearer 제거한 토큰으로 사용자 정보 조회
        UserResponseDto userInfo = authService.getUserInfo(token);
        return ResponseEntity.ok(userInfo); // 사용자 정보 반환
    }
}
