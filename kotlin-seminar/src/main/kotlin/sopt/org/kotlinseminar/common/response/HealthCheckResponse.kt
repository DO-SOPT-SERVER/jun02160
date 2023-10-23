package sopt.org.kotlinseminar.common.response

class HealthCheckResponse(
    var status: String
) {

    val STATUS_OK = "OK"   // 타입을 생략해도 컴파일러의 분석으로 String으로 인식

    fun HealthCheckResponse() {
        this.status = STATUS_OK;
    }
}