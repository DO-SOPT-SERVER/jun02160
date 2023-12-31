package com.server.dosopt.seminar.controller;

import com.server.dosopt.seminar.dto.request.member.MemberCreateRequest;
import com.server.dosopt.seminar.dto.request.member.MemberProfileUpdateRequest;
import com.server.dosopt.seminar.dto.response.member.MemberGetResponse;
import com.server.dosopt.seminar.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    //조회
    @GetMapping("/{memberId}/v2")
    public ResponseEntity<MemberGetResponse> getMemberProfileV1(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMemberByIdV1(memberId));
    }

    // consume: 요청에 들어오는 값 => GET으로 단순 조회하는 작업이므로 produces 실질적으로 사용됨
    @GetMapping(value = "/{memberId}/v2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)  // 안전성을 위해 ENUM으로 작성하자 (직접 작성하는 일 지양)
    public ResponseEntity<MemberGetResponse> getMemberProfileV2(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMemberByIdV2(memberId));
    }

    @GetMapping  // 목록 조회 - 복수형(s)로 명시적인 표현
    public ResponseEntity<List<MemberGetResponse>> getMembersProfile() {
        return ResponseEntity.ok(memberService.getMembers());
    }

    // 생성
    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberCreateRequest request) {
        URI location = URI.create("api/member/" + memberService.create(request));
        return ResponseEntity.created(location).build();
    }

    // 수정
    @PatchMapping("/{memberId}")
    public ResponseEntity<Void> updateMemberSoptInfo(@PathVariable Long memberId, @RequestBody MemberProfileUpdateRequest request) {
        memberService.updateSOPT(memberId, request);
        return ResponseEntity.noContent().build();
    }

    // 삭제
    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

}
