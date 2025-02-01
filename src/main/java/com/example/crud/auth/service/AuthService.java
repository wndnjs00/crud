package com.example.crud.auth.service;

import com.example.crud.auth.dto.LoginRequestDto;
import com.example.crud.auth.dto.RegisterRequestDto;
import com.example.crud.auth.dto.UserResponseDto;
import com.example.crud.auth.model.User;
import com.example.crud.auth.repository.AuthRepository;
import com.example.crud.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
// 비즈니스 로직을 처리
public class AuthService {

    private final AuthRepository authRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    // 사용자 회원가입 로직 수행
    public void registerUser(RegisterRequestDto registerRequestDto) {

        /* validation (유효성 검사) */
        /* 이메일 중복체크 */
        if (authRepository.existsByEmail(registerRequestDto.getEmail())){
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        /* 가공 */
        /* 비밀번호 암호화 후 사용자 정보 가져옴 */
        User user = User.builder()
                .email(registerRequestDto.getEmail())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))  //비밀번호 암호화
                .name(registerRequestDto.getName())
                .phone(registerRequestDto.getPhone())
                .address(registerRequestDto.getAddress())
                .build();

        /* 결과 */
        /* userRepository를 통해 사용자 데이터(user데이터)를 DB에 저장*/
        authRepository.save(user);
    }



    // 로그인 로직 수행
    // 사용자 인증 및 Access,Refresh Token 생성
    public Map<String, Object> loginUser(LoginRequestDto loginRequestDto){

        /* 이메일로 사용자 검색 */
        User user = authRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        /* 비밀번호 검증 */
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        /* 토큰 생성 */
        /* accessToken, refreshToken 생성  */
        String accessToken = jwtUtil.createAccessToken(user.getEmail());
        String refreshToken = jwtUtil.createRefreshToken(user.getEmail());

        /* 응답 데이터 구성 */
        Map<String, Object> response = new HashMap<>();
        response.put("accessToken", accessToken);
        response.put("refreshToken", refreshToken);
        return response;
    }



    // 토큰을 이용해 사용자 정보 조회
    public UserResponseDto getUserInfo(String token) {
        /* JwtUtil을 이용해 토큰에서 이메일 추출 */
        String email = jwtUtil.getEmailFromToken(token);

        /* 이메일로 사용자 정보 조회 */
        User user = authRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        /* 사용자 정보 응답 반환 */
        return UserResponseDto.fromEntity(user);
    }
}
