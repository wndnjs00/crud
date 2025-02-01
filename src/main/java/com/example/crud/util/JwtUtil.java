package com.example.crud.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


// 토큰 생성,검증,파싱
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private final long ACCESS_TOKEN_VALIDITY = 30 * 60 * 1000; // 30분
    private final long REFRESH_TOKEN_VALIDITY = 7 * 24 * 60 * 60 * 1000; // 1주일


    // Key 객체 생성
    private Key getSigningKey() {
        System.out.println("Secret Key Used:" + secret);
        return new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());
    }


    private String createToken(String email, long validity) {
        Claims claims = Jwts.claims().setSubject(email);    // 이메일 정보 저장
        Date now = new Date();
        Date expiry = new Date(now.getTime() + validity);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact(); // JWT 생성
    }

    // Access Token 생성
    public String createAccessToken(String email) {
        return createToken(email, ACCESS_TOKEN_VALIDITY);
    }

    // Refresh Token 생성
    public String createRefreshToken(String email) {
        return createToken(email, REFRESH_TOKEN_VALIDITY);
    }

    // 토큰에서 이메일 추출
    public String getEmailFromToken(String token) {
        try {
            System.out.println("Parsing Token:" +token.trim()); // 추가
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token.trim())
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            System.err.println("JWT 토큰이 유효하지 않음:" + e.getMessage());
            throw new IllegalArgumentException("JWT 토큰이 유효하지 않습니다:" + e.getMessage());
        }
    }

    // 토큰의 유효성 검증
    public boolean validateToken(String token) {
        try {
            System.out.println("Validating Token:" +token.trim());
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token.trim());
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.err.println("JWT 검증 실패:" + e.getMessage());
            return false;
        }
    }

    // Authorization 헤더에서 Bearer Token 추출
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {

            // Bearer 이후의 토큰부분 추출 및 공백제거
            String token = bearerToken.substring("Bearer ".length()).trim();
//            System.out.println("Resolved Token (after trimming):" + token);
            return token;
        }
        return null;
    }

    // Authorization 헤더 값(String)을 처리하는 오버로딩 메서드 추가
    public String resolveToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring("Bearer ".length()).trim();
        }
        return null;
    }
}
