package com.server.dosopt.seminar.service;

import com.server.dosopt.seminar.domain.Member;
import com.server.dosopt.seminar.dto.request.member.MemberCreateRequest;
import com.server.dosopt.seminar.dto.request.member.MemberProfileUpdateRequest;
import com.server.dosopt.seminar.dto.response.member.MemberGetResponse;
import com.server.dosopt.seminar.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)   // 클래스보다 메서드 레벨의 @Transactional이 우선적으로 적용 => 메서드 단에 붙여주면 읽기전용의 트랜잭션 X
public class MemberService {

    private final MemberRepository memberRepository;

    // 조회
    public MemberGetResponse getMemberByIdV1(Long memberId) {
        return MemberGetResponse.of(findMemberById(memberId));
    }

    public MemberGetResponse getMemberByIdV2(Long memberId) {
        return MemberGetResponse.of(memberRepository.findByIdOrThrow(memberId));
    }

    public List<MemberGetResponse> getMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberGetResponse::of)
                .collect(Collectors.toList());
    }


    // 생성
    @Transactional
    public String create(MemberCreateRequest request) {
        Member member = Member.builder()
                .name(request.name())
                .nickname(request.nickname())
                .age(request.age())
                .sopt(request.sopt())
                .build();
        return memberRepository.save(member).getId().toString();
    }

    // 수정
    @Transactional
    public void updateSOPT(Long memberId, MemberProfileUpdateRequest request) {
        Member findMember = findMemberById(memberId);
        findMember.getSopt().updateSopt(request.generation(), request.part());
        memberRepository.save(findMember);

    }

    // 삭제
    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));
    }



}
