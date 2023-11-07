package sopt.org.kotlinseminar.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sopt.org.kotlinseminar.common.response.HealthCheckResponse
import sopt.org.kotlinseminar.domain.Person

@RestController
@RequestMapping("/health")
class HealthCheckController {

    @GetMapping("/v1")
    fun healthCheckV1(): Map<String, String> {
        val response = HashMap<String, String>()
        response.put("status", "OK")
        return response
    }

    @GetMapping("/v2")
    fun healthCheckV2(): ResponseEntity<String> {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/v3")
    fun healthCheckV3(): String {
        return "OK";
    }

    @GetMapping("/v4")
    fun healthCheckV4(): ResponseEntity<Map< String, String>> {
        val response = HashMap<String, String>()
        response.put("status", "OK")
        return ResponseEntity.ok(response);
    }

    @GetMapping("/v5")
    fun healthCheckV5(): ResponseEntity<HealthCheckResponse> {
        return ResponseEntity.ok(HealthCheckResponse("OK"))
    }

    @GetMapping("/v6")
    fun healthCheckV6(): ResponseEntity<Person> {
        val person = Person("예준", "박")
        return ResponseEntity.ok(person);
    }
}