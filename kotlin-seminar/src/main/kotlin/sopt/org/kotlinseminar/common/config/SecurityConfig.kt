package sopt.org.kotlinseminar.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import sopt.org.kotlinseminar.common.util.auth.filter.CustomAccessDeniedHandler
import sopt.org.kotlinseminar.common.util.auth.filter.CustomJwtAuthenticationEntryPoint
import sopt.org.kotlinseminar.common.util.auth.filter.JwtAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig(
        private val jwtAuthenticationFilter: JwtAuthenticationFilter,
        private val customJwtAuthenticationEntryPoint: CustomJwtAuthenticationEntryPoint,
        private val customAccessDeniedHandler: CustomAccessDeniedHandler
) {

    private val AUTH_WHITELIST = arrayOf(
            "/sign-up",
            "/sign-in"
    )

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {



        http.csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        // 엔드포인트 Auth 설정
        http.authorizeHttpRequests(Customizer { auth ->
            auth.requestMatchers(*AUTH_WHITELIST).permitAll()
            auth.anyRequest().authenticated()
        })

        http.exceptionHandling()
                .authenticationEntryPoint(customJwtAuthenticationEntryPoint)
                .accessDeniedHandler(customAccessDeniedHandler)
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            // CORS 에러 방지를 위한 메서드 오버라이딩
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                        .allowedOrigins("*") // CORS를 포함한 모든 요청 수락!
                        .allowedOriginPatterns("*")
                        .allowedMethods("*")
            }
        }
    }
}