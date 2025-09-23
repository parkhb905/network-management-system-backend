package com.network.nms.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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

    @Value("${jwt.expiration}")
    private long validityInMs;

    /**
     * 토큰 생성
     * @param authentication
     * @return
     */
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDt = new Date(now.getTime() + validityInMs);

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

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}