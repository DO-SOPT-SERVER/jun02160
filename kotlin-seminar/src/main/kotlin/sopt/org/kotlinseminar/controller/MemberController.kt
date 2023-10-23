package sopt.org.kotlinseminar.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sopt.org.kotlinseminar.dto.request.MemberCreateRequest
import sopt.org.kotlinseminar.dto.response.MemberGetResponse
import sopt.org.kotlinseminar.service.MemberService
import java.net.URI


@RestController
@RequestMapping("/api/member")
class MemberController {

    @Autowired
    lateinit var memberService: MemberService


    // 생성
    @PostMapping
    fun createMember(@RequestBody request: MemberCreateRequest) : ResponseEntity<Void> {
        val location = URI.create("api/member/" + memberService.create(request))
        return ResponseEntity.created(location).build();
    }


    // 조회
    @GetMapping("/{memberId}/v2")
    fun getMemberProfileV1(@PathVariable memberId: Long?): ResponseEntity<MemberGetResponse?>? {
        return ResponseEntity.ok(memberService.getMemberByIdV1(memberId))
    }

    @GetMapping(
        value = ["/{memberId}/v2"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getMemberProfileV2(@PathVariable memberId: Long?): ResponseEntity<MemberGetResponse?>? {
        return ResponseEntity.ok(memberService.getMemberByIdV2(memberId))
    }

    @GetMapping // 목록 조회
    fun getMembersProfile(): ResponseEntity<List<MemberGetResponse?>?>? {
        return ResponseEntity.ok(memberService.getMembers())
    }
}