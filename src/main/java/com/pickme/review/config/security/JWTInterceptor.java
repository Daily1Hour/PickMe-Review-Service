package com.pickme.review.config.security;

import com.pickme.review.service.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class JWTInterceptor implements HandlerInterceptor {

    private final JWTService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
            return true;
        }

        // 클라이언트 요청 헤더에서 Authorization 정보를 가져옴
        String token = request.getHeader("Authorization");

        // Authorization 헤더가 없거나 Bearer 형식이 아니면 401 상태코드 반환하고 요청 중단
        if(token == null || !token.startsWith("Bearer ")){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 토큰에서 사용자 정보를 추출
        String clientId = jwtService.extractToken(token);

        // HttpServletRequest에 사용자 정보를 속성으로 추가하여 컨트롤러에서 사용할 수 있게 함
        request.setAttribute("clientId", clientId);

        // 요청 처리를 계속 진행
        return true;

    }

}
