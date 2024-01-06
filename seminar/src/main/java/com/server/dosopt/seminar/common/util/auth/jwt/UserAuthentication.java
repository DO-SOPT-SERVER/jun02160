package com.server.dosopt.seminar.common.util.auth.jwt;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class UserAuthentication extends UsernamePasswordAuthenticationToken {

    // 사용자 인증 객체 생성

    public UserAuthentication(Object principal, Object credentials,
                                Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
