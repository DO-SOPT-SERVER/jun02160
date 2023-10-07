package com.server.dosopt.seminar.controller;

import com.server.dosopt.seminar.dto.HealthCheckResponse;
import com.server.dosopt.seminar.service.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

    @GetMapping("/v1")
    public Map<String, String> healthCheckV1() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        return response;
    }

    @GetMapping("/v2")
    public ResponseEntity<String> healthCheckV2() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/v3")
    public String healthCheckV3() {
        return "OK";
    }

    @GetMapping("/v4")
    public ResponseEntity<Map<String, String>> healthCheckV4() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/v5")
    public ResponseEntity<HealthCheckResponse> healthCheckV5() {
        return ResponseEntity.ok(new HealthCheckResponse());
    }

    @GetMapping("/v6")
    public ResponseEntity<HealthCheckResponse> healthCheckV6() {

        // 빌더패턴을 이용한 객체 생성 -> 어떤 멤버에 어떤 값을 주는지 직관적으로 확인이 가능!
        Person person = new Person("예준", "박");   // 어떤 게 성이고, 어떤 게 이름?
        Person personByBuilder = Person.builder()
                .firstName("박")
                .lastName("예준")
                .build();   // 순서 바뀌어도 ㄱㅊ

        return ResponseEntity.ok(new HealthCheckResponse("OK"));
    }
}
