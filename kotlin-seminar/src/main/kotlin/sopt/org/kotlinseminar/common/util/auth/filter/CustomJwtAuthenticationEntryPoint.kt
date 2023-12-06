package sopt.org.kotlinseminar.common.util.auth.filter

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class CustomJwtAuthenticationEntryPoint: AuthenticationEntryPoint {

    override fun commence(request: HttpServletRequest?, response: HttpServletResponse?, authException: AuthenticationException?) {
        setResponse(response)
    }

    private fun setResponse(response: HttpServletResponse?) {
        response?.status = HttpServletResponse.SC_UNAUTHORIZED
    }
}