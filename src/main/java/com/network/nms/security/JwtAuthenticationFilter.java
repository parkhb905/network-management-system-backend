package com.network.nms.security;

import com.network.nms.exception.ErrorCode;
import com.network.nms.mapper.log.UserAccessLogMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;

    private final UserAccessLogMapper userAccessLogMapper;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider,
                                   UserDetailsService userDetailsService,
                                   UserAccessLogMapper userAccessLogMapper) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
        this.userAccessLogMapper = userAccessLogMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                String username = jwtTokenProvider.getUsernameFromToken(token);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // JWT가 유효하면 해당 사용자(UserDetails)로 인증 객체를 만들어 스프링 시큐리티 컨텍스트에 등록.
                if (jwtTokenProvider.validateToken(token, username, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch(ExpiredJwtException e) { // 액세스 토큰 만료
                handleJwtException(response, ErrorCode.AUTH_EXPIRED_TOKEN);

                String username = e.getClaims().getSubject();
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (userDetails instanceof CustomUserDetails customUserDetails) {
                    userAccessLogMapper.updateLogoutAt(customUserDetails.getUserId());
                }

                return; // 필터 체인 중단
            } catch (Exception e) {
                logger.warn("JWT validation failed: " + e.getMessage());
                handleJwtException(response, ErrorCode.AUTH_FORBIDDEN);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void handleJwtException(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setStatus(errorCode.getStatus().value());
        response.setContentType("application/json;charset=UTF-8");

        String body = String.format(
                "{\"code\": %d, \"message\": \"%s\"}",
                errorCode.getCode(),
                errorCode.getMessage()
        );

        response.getWriter().write(body);
    }

}