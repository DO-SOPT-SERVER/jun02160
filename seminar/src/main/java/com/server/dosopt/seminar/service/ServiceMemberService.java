package com.server.dosopt.seminar.service;

import com.server.dosopt.seminar.common.exception.BusinessException;
import com.server.dosopt.seminar.domain.ServiceMember;
import com.server.dosopt.seminar.dto.request.servicemember.ServiceMemberRequest;
import com.server.dosopt.seminar.repository.ServiceMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServiceMemberService {

    private final ServiceMemberRepository serviceMemberRepository;
    private final PasswordEncoder passwordEncoder;  // 비밀번호 암호화를 위한 주입 -> 암호화를 할 때마다 다른 문자열로 바뀜

    @Transactional
    public String create(ServiceMemberRequest request) {
        ServiceMember serviceMember = ServiceMember.builder()
                .nickanme(request.nickname())
                .password(passwordEncoder.encode(request.password()))  // 암호화하여 저장
                .build();
        serviceMemberRepository.save(serviceMember);

        return serviceMember.getId().toString();
    }

    public void signIn(ServiceMemberRequest request) {
        ServiceMember serviceMember = serviceMemberRepository.findByNickname(request.nickname())
                .orElseThrow(() -> new BusinessException("해당하는 회원이 없습니다."));
        if (!passwordEncoder.matches(request.password(), serviceMember.getPassword())) {   // 비밀번호 일치 여부 확인
            throw new BusinessException("비밀번호가 일치하지 않습니다.");
        }
    }
}
