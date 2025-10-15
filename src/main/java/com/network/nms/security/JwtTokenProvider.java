package com.network.nms.security;

import com.network.nms.exception.CustomException;
import com.network.nms.exception.ErrorCode;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access-expiration}")
    private long accessTokenValidityInMs;

    @Value("${jwt.refresh-expiration}")
    private long refreshTokenValidityInMs;

    /**
     * 액세스 토큰 생성
     * @param authentication
     * @return
     */
    public String generateAccessToken(String username) {
        Date now = new Date();
        Date expiryDt = new Date(now.getTime() + accessTokenValidityInMs);

        return Jwts.builder()
                .setSubject(username)                           // sub
                .setIssuedAt(now)                               // iat
                .setExpiration(expiryDt)                        // exp
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 서명 
                .compact();
    }

    /**
     * 리프레시 토큰 생성
     * @param authentication
     * @return
     */
    public String generateRefreshToken(String username) {
        Date now = new Date();
        Date expiryDt = new Date(now.getTime() + refreshTokenValidityInMs);

        return Jwts.builder()
                .setSubject(username)                           // sub
                .setIssuedAt(now)                               // iat
                .setExpiration(expiryDt)                        // exp
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 서명
                .compact();
    }

    /**
     * Claims 파싱
     * @param token
     * @return
     */
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 토큰에서 username 추출
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * 토큰 만료 여부
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token) {
        try {
            Date expiration = getClaims(token).getExpiration();
            return expiration.before(new Date());
        } catch(ExpiredJwtException e) {
            return true;
        }
    }

    /**
     * 토큰 유효성 검증
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, String username, UserDetails userDetails) {
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Claim 파싱
     * @param token
     * @return
     */
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * RefreshToken 전용 — DB 조회 없이 단순 유효성만 검증
     */
    public boolean validateTokenOnly(String token) {
        try {
            parseClaims(token); // 서명, 만료 등 검증
            return true;
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.AUTH_EXPIRED_TOKEN);
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException(ErrorCode.AUTH_FORBIDDEN);
        }
    }

}