package com.server.dosopt.seminar.common.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.server.dosopt.seminar.common.util.auth.filter.CustomAccessDeniedHandler;
import com.server.dosopt.seminar.common.util.auth.filter.CustomJwtAuthenticationEntryPoint;
import com.server.dosopt.seminar.common.util.auth.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomJwtAuthenticationEntryPoint customJwtAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private static final String[] AUTH_WHITELIST = {
            "/api/users/sign-up",
            "/api/users/sign-in"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()

                // JWT를 사용하여 세션은 사용하지 않는다면 아래와 같이 동작하지 않음을 명시
                .sessionManagement()
                .sessionCreationPolicy(STATELESS)

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customJwtAuthenticationEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler)

                .and()
                .authorizeHttpRequests()
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()  // WHITE_LIST에 포함되지 않는 나머지 요청은 모두 인증 필요!

                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .build();   // -> 스프링 빈으로 등록, 어플리케이션 실행 시 자동으로 필터에 등록된다!
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            // CORS 에러 방지를 위한 메서드 오버라이딩
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")   // CORS를 포함한 모든 요청 수락!
                        .allowedOriginPatterns("*")
                        .allowedMethods("*");
            }
        };
    }
}
