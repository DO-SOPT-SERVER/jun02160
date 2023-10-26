package com.server.dosopt.seminar.controller;

import static com.server.dosopt.seminar.enums.Success.CREATE_MEMBER_SUCCESS;
import static com.server.dosopt.seminar.enums.Success.DELETE_MEMBER_SUCCESS;
import static com.server.dosopt.seminar.enums.Success.GET_MEMBER_INFO_SUCCESS;
import static com.server.dosopt.seminar.enums.Success.GET_MEMBER_LIST_SUCCESS;
import static com.server.dosopt.seminar.enums.Success.UPDATE_MEMBER_SUCCESS;

import com.server.dosopt.seminar.common.response.ApiResponse;
import com.server.dosopt.seminar.dto.request.MemberCreateRequest;
import com.server.dosopt.seminar.dto.request.MemberProfileUpdateRequest;
import com.server.dosopt.seminar.dto.response.MemberGetResponse;
import com.server.dosopt.seminar.enums.Success;
import com.server.dosopt.seminar.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
