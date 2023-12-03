package com.server.dosopt.seminar.dto;

public class HealthCheckResponse {

    private String status;
    private final String STATUS_OK = "OK";

    public HealthCheckResponse(String status) {
        this.status = status;
    }

    public HealthCheckResponse() {
        this.status = STATUS_OK;
    }
}
