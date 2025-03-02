package com.example.crud.auth.service;

import com.example.crud.auth.dto.LoginRequestDto;
import com.example.crud.auth.dto.RegisterRequestDto;
import com.example.crud.auth.dto.UserResponseDto;
import com.example.crud.auth.model.User;
import com.example.crud.auth.repository.AuthRepository;
import com.example.crud.util.JwtUtil;
import io.jsonwebtoken.JwtException;
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

        /* Refresh Token 저장 */
        user.setRefreshToken(refreshToken);
        authRepository.save(user); // Refresh Token을 DB에 저장

        /* 응답 데이터 구성 */
        Map<String, Object> response = new HashMap<>();
        response.put("accessToken", accessToken);
        response.put("refreshToken", refreshToken);
        return response;
    }


    // 토큰 재발급 로직
    // Refresh Token이 유효한 경우, 새로운 Access Token과 Refresh Token을 재발급
    public Map<String, Object> refreshTokens(String refreshToken) {

        try {
            /* Refresh Token validation (유효성 검사) */
            if (!jwtUtil.validateToken(refreshToken)) {
                throw new IllegalArgumentException("Refresh Token이 만료되었습니다.");
            }

            /* Refresh Token으로 사용자 검색 */
            User user = authRepository.findByRefreshToken(refreshToken)
                    .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 Refresh Token입니다."));

            /* 새로운 토큰 생성 */
            /* 새로운 accessToken, refreshToken 생성 */
            String newAccessToken = jwtUtil.createAccessToken(user.getEmail());
            String newRefreshToken = jwtUtil.createRefreshToken(user.getEmail());

            /* 새로운 Refresh Token 저장 */
            user.setRefreshToken(newRefreshToken);
            authRepository.save(user);  // Refresh Token을 DB에 저장 (새로운 Refresh Token으로 갱신)

            /* 응답 데이터 구성 */
            Map<String, Object> response = new HashMap<>();
            response.put("accessToken", newAccessToken);
            response.put("refreshToken", newRefreshToken);
            return response;

        } catch (JwtException e) {
            throw new IllegalArgumentException("유효하지 않은 Refresh Token입니다."); // 형식적으로 잘못된 토큰
        } catch (IllegalArgumentException e) {
            throw e; // "Refresh Token이 만료되었습니다." 메시지 처리
        }
    }

    // Refresh Token 만료시, 로그아웃 처리
    public void handleExpiredRefreshToken(String refreshToken){
        User user = authRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 Refresh Token입니다."));

        user.setRefreshToken(null);
        authRepository.save(user); // Refresh Token 제거
    }


    // 로그아웃 로직
    public void logoutUser(String email){
        User user = authRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        /* DB에서 Refresh Token 삭제 */
        user.setRefreshToken(null);
        authRepository.save(user);
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
