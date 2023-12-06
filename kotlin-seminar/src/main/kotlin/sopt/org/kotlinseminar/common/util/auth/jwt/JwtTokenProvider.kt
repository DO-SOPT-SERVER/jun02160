package sopt.org.kotlinseminar.common.util.auth.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey

@Component
@RequiredArgsConstructor
class JwtTokenProvider {

    @Value("\${jwt.secret}")
    private var JWT_SECRET: String = ""
    @PostConstruct
    protected fun init() {
        JWT_SECRET = Base64.getEncoder().encodeToString(JWT_SECRET.toByteArray(StandardCharsets.UTF_8))
    }

    fun generateToken(authentication: Authentication, tokenExpirationTime: Long): String {
        val now = Date()
        val claims: Claims = Jwts.claims()
                .setIssuedAt(now)
                .setExpiration(Date(now.getTime() + tokenExpirationTime)) // 만료 시간
        claims.put(MEMBER_ID, authentication.principal)
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // Header
                .setClaims(claims) // Claim
                .signWith(signingKey) // Signature
                .compact()
    }

    private val signingKey: SecretKey
        private get() {
            val encodedKey = Base64.getEncoder().encodeToString(JWT_SECRET.toByteArray())
            return Keys.hmacShaKeyFor(encodedKey.toByteArray())
        }

    fun validateToken(token: String?): JwtValidationType {
        return try {
            val claims: Claims = getBody(token)
            JwtValidationType.VALID_JWT
        } catch (ex: MalformedJwtException) {
            JwtValidationType.INVALID_JWT_TOKEN
        } catch (ex: ExpiredJwtException) {
            JwtValidationType.EXPIRED_JWT_TOKEN
        } catch (ex: UnsupportedJwtException) {
            JwtValidationType.UNSUPPORTED_JWT_TOKEN
        } catch (ex: IllegalArgumentException) {
            JwtValidationType.EMPTY_JWT
        }
    }

    private fun getBody(token: String?): Claims {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
    }

    fun getUserFromJwt(token: String?): Long {
        val claims: Claims = getBody(token)
        return java.lang.Long.valueOf(claims.get(MEMBER_ID).toString())
    }

    companion object {
        private const val MEMBER_ID = "memberId"
    }
}