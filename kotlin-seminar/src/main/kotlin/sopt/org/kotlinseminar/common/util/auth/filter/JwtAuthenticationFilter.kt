package sopt.org.kotlinseminar.common.util.auth.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import sopt.org.kotlinseminar.common.util.auth.jwt.JwtTokenProvider
import sopt.org.kotlinseminar.common.util.auth.jwt.JwtValidationType
import sopt.org.kotlinseminar.common.util.auth.jwt.UserAuthentication

@Component
@RequiredArgsConstructor
class JwtAuthenticationFilter(
        private val jwtTokenProvider: JwtTokenProvider
): OncePerRequestFilter() {


    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val token: String? = getJwtFromRequest(request)
            if (jwtTokenProvider.validateToken(token) == JwtValidationType.VALID_JWT) {
                val memberId = jwtTokenProvider.getUserFromJwt(token)

                val authentication = UserAuthentication(memberId.toString(), null, null)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            throw RuntimeException()
        }

        filterChain.doFilter(request, response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring("Bearer ".length)
        } else null
    }
}