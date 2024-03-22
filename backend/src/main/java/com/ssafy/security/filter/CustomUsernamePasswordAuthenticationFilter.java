package com.ssafy.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.domain.users.dto.request.RegisterRequest;
import com.ssafy.security.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
public class CustomUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    private static final String DEFAULT_LOGIN_REQUEST_URL = "/auth/login";  // /auth/login으로 오는 요청을 처리
    private static final String HTTP_METHOD = "POST";    //HTTP 메서드의 방식은 POST
    private static final String CONTENT_TYPE = "application/json";//json 타입의 데이터로만 로그인을 진행


    private static final AntPathRequestMatcher ANT_PATH_REQUEST_MATCHER =
            new AntPathRequestMatcher(DEFAULT_LOGIN_REQUEST_URL, HTTP_METHOD);

    public CustomUsernamePasswordAuthenticationFilter(JwtUtil jwtUtil, ObjectMapper objectMapper) {
        super(ANT_PATH_REQUEST_MATCHER);
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
    };


    /**
     * 사용자의 로그인 요청을 처리하는 필터.
     * HTTP 요청을 받아 적절한 요청인지 검증한 후
     * 유효한 사용자인지 확인.
     * 확인된 정보를 기반으로 인증 객체를 생성하여
     * AuthenticationManager에 전달.
     *
     * @param request  HTTP 요청 객체
     * @param response HTTP 응답 객체
     * @return Authentication 객체
     * @throws AuthenticationException 인증에 실패한 경우 발생하는 예외
     * @throws IOException              입출력 오류가 발생한 경우 발생하는 예외
     * @throws ServletException         서블릿 오류가 발생한 경우 발생하는 예외
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.info("== 로그인 필터 ==");
        log.info("Request method: {}", request.getMethod());
        log.info("Content-Type: {}", request.getHeader("Content-Type"));
        // 사용자 정보를 기반으로 인증 시도
        if(!request.getMethod().equals(HTTP_METHOD)) {
            throw new AuthenticationServiceException("post 요청이 아닙니다");
        }

        if(!request.getHeader("Content-Type").equals(CONTENT_TYPE)) {
            throw new AuthenticationServiceException("json타입 요청이 아닙니다");
        }

        RegisterRequest registerRequest = objectMapper.readValue(request.getInputStream(), RegisterRequest.class);


        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                registerRequest.getEmail(),
                registerRequest.getPassword()
        );
        log.info("registerRequest {}", authRequest);

        // 인증 성공 시 Authentication 객체 반환
        return getAuthenticationManager().authenticate(authRequest);
    }


}
