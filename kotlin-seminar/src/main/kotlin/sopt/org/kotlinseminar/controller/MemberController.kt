package sopt.org.kotlinseminar.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sopt.org.kotlinseminar.dto.request.MemberCreateRequest
import sopt.org.kotlinseminar.service.MemberService
import java.net.URI

@RestController
@RequestMapping("/api/member")
class MemberController {

    @Autowired
    lateinit var memberService: MemberService


    @PostMapping
    fun createMember(@RequestBody request: MemberCreateRequest) : ResponseEntity<Void> {
        val location = URI.create("api/member/" + memberService.create(request))
        return ResponseEntity.created(location).build();
    }
}