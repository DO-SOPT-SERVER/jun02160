package com.server.dosopt.seminar.controller;

import com.server.dosopt.seminar.dto.request.servicemember.ServiceMemberRequest;
import com.server.dosopt.seminar.dto.response.servicemember.ServiceMemberResponse;
import com.server.dosopt.seminar.service.ServiceMemberService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/")
public class ServiceMemberController {

    private final ServiceMemberService serviceMemberService;

    @PostMapping("sign-up")
    public ResponseEntity<Void> signUp(@RequestBody ServiceMemberRequest request) {
        URI location = URI.create(serviceMemberService.create(request));
        return ResponseEntity.created(location).build();
    }

    @PostMapping("sign-in")
    public ResponseEntity<ServiceMemberResponse> signIn(@RequestBody ServiceMemberRequest request) {
        return ResponseEntity.ok().body(serviceMemberService.signIn(request));
    }
}
