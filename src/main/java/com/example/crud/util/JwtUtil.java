package com.example.crud.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component     // Spring의 Bean으로 등록되며, DI(의존성 주입)를 통해 다른 클래스에서 사용됨
public class JwtUtil {
    @Value("${jwt.secret}")
    // application.properties에서 jwt.secret키에 설정된 값을 불러옴 / 토큰의 검증에 사용되는 비밀키
    private String secret;
    
    private final long validityInMilliseconds = 3600000; // 토큰의 유효시간 = 1시간

    // JWT 생성
    public String createToken(String email) {
        Claims claims = Jwts.claims().setSubject(email);    // JWT의 Payload를 구성하는 데이터   // setSubject(email)를 통해 토큰의 주체(subject)를 이메일로 설정
        Date now = new Date();  // now: 토큰 생성 시간
        Date validity = new Date(now.getTime() + validityInMilliseconds);   // validity: 토큰 만료 시간(생성 시간 + 유효 시간)

        return Jwts.builder()
            .setClaims(claims)  // 위에서 설정한 클레임(Payload)을 추가
            .setIssuedAt(now)   // 토큰 생성 시간을 설정
            .setExpiration(validity)    //토큰 만료 시간을 설정
            .signWith(SignatureAlgorithm.HS256, secret.getBytes())  // 비밀키와 HMAC SHA-256 알고리즘을 사용하여 서명
            .compact(); // 토큰을 문자열로 압축하여 반환
    }

    // JWT에서 이메일 추출
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder() // JWT 파서를 생성
            .setSigningKey(secret.getBytes())   // 비밀키를 사용하여 토큰의 서명을 검증
            .build()
            .parseClaimsJws(token)  // 입력된 토큰을 파싱하고, 클레임(Payload)을 추출
            .getBody().getSubject(); // JWT의 subject 필드(이메일)를 반환
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()    // JWT 파서를 생성
                .setSigningKey(secret.getBytes()) // 비밀 키를 사용하여 서명을 검증
                .build()
                .parseClaimsJws(token); // 토큰을 파싱하여 유효성을 확인 // 만료된 토큰이나 잘못된 서명이 포함된 토큰은 예외를던짐
            return true;
        } catch (JwtException | IllegalArgumentException e) {   // JwtException: 서명이 잘못되었거나 토큰이 만료된 경우 발생   // IllegalArgumentException: 입력된 토큰이 null이거나 비어 있는 경우 발생
            return false;
        }
    }
}
