package com.example.crud.controller;

import com.example.crud.mapper.UserMapper;
import com.example.crud.model.User;
import com.example.crud.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private final UserMapper mapper;
    private final JwtUtil jwtUtil;

    // 의존성 주입(DI)를 통해 UserMapper와 JwtUtil을 주입받음
    public UserController(UserMapper mapper, JwtUtil jwtUtil) {
        this.mapper = mapper;
        this.jwtUtil = jwtUtil;
    }

    // 회원가입 API
    @PostMapping("/user/register")
    public ResponseEntity<String> registerUser(@RequestParam("email") String email,
                                               @RequestParam("password") String password,
                                               @RequestParam("name") String name,
                                               @RequestParam("phone") String phone,
                                               @RequestParam("address") String address){
        try {
            mapper.insertUser(email, password, name, phone, address);
            return ResponseEntity.ok("회원가입 성공");    // postman으로 API테스트시 성공하면 뜨도록 메시지설정
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패" + e.getMessage());
        }
    }

    // 로그인 API
    @PostMapping("/user/login")
    public ResponseEntity<Map<String, Object>> loginUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        // 이메일과 비밀번호를 기반으로 사용자 정보를 데이터베이스에서 조회
        User user = mapper.loginUser(email, password);
        
        if (user != null) {
            // 인증 성공 시 이메일을 기반으로 JWT를 생성
            String token = jwtUtil.createToken(user.getEmail());

            // 사용자 정보와 JWT를 포함한 JSON 객체를 반환     // token,user객체(email,name,phone,address)
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", user);
            
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    // 사용자 정보 조회 API (토큰 검증 포함)
    @GetMapping("/user/info")
    public ResponseEntity<User> getUserInfo(
            // @RequestHeader("Authorization"): 요청 헤더에서 Authorization 값을 읽어옴
            @RequestHeader("Authorization") String token) {

        // Bearer <token> 형식으로 토큰이 전달됨
        // 토큰에서 Bearer 부분을 제거하고 실제 JWT 값만 추출
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            // 토큰의 유효성을 확인 -> 유효한 토큰일 경우 true 반환
            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.getEmailFromToken(token); // JWT에서 이메일 정보를 추출
                User user = mapper.getUserByEmail(email);   // 이메일을 기준으로 데이터베이스에서 사용자 정보를 조회
                return ResponseEntity.ok(user); // 성공시 -> 사용자 정보를 JSON 형식으로 반환
            }
        }
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();  // 실패시 -> HTTP 상태 코드 401 (Unauthorized) 반환
    }
}
