package sopt.org.kotlinseminar.controller

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sopt.org.kotlinseminar.dto.request.servicemember.ServiceMemberRequest
import sopt.org.kotlinseminar.service.ServiceMemberService
import java.net.URI


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/")
class ServiceMemberController(
        private val serviceMemberService: ServiceMemberService
) {
    @PostMapping("sign-up")
    fun signUp(@RequestBody request: ServiceMemberRequest): ResponseEntity<Void> {
        val location: URI = URI.create(serviceMemberService.create(request))
        return ResponseEntity.created(location).build()
    }

    @PostMapping("sign-in")
    fun signIn(@RequestBody request: ServiceMemberRequest): ResponseEntity<Void> {
        serviceMemberService.signIn(request)
        return ResponseEntity.noContent().build()
    }
}