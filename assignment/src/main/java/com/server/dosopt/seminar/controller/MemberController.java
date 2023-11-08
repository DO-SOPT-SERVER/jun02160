package com.server.dosopt.seminar.controller;

import static com.server.dosopt.seminar.enums.SuccessMessage.*;

import com.server.dosopt.seminar.common.response.ApiResponse;
import com.server.dosopt.seminar.dto.request.member.MemberCreateRequest;
import com.server.dosopt.seminar.dto.request.member.MemberProfileUpdateRequest;
import com.server.dosopt.seminar.dto.response.member.MemberGetResponse;
import com.server.dosopt.seminar.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    //조회
    @GetMapping("/{memberId}/v1")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<MemberGetResponse> getMemberProfileV1(@PathVariable Long memberId) {
        return ApiResponse.success(GET_MEMBER_INFO_SUCCESS, memberService.getMemberByIdV1(memberId));
    }

    // consume: 요청에 들어오는 값 => GET으로 단순 조회하는 작업이므로 produces 실질적으로 사용됨
    @GetMapping(value = "/{memberId}/v2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)  // 안전성을 위해 ENUM으로 작성하자 (직접 작성하는 일 지양)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<MemberGetResponse> getMemberProfileV2(@PathVariable Long memberId) {
        return ApiResponse.success(GET_MEMBER_INFO_SUCCESS, memberService.getMemberByIdV2(memberId));
    }

    @GetMapping  // 목록 조회 - 복수형(s)로 명시적인 표현
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<MemberGetResponse>> getMembersProfile() {
        return ApiResponse.success(GET_MEMBER_LIST_SUCCESS, memberService.getMembers());
    }

    // 생성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<String> createMember(@RequestBody MemberCreateRequest request) {
        /*URI location = URI.create("api/member/" + memberService.create(request));
        return ResponseEntity.created(location).build();*/
        return ApiResponse.success(CREATE_MEMBER_SUCCESS, memberService.create(request));
    }

    // 수정
    @PatchMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse updateMemberSoptInfo(@PathVariable Long memberId, @RequestBody MemberProfileUpdateRequest request) {
        memberService.updateSOPT(memberId, request);
        return ApiResponse.success(UPDATE_MEMBER_SUCCESS);
    }

    // 삭제
    @DeleteMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ApiResponse.success(DELETE_MEMBER_SUCCESS);
    }

}
