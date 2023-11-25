package com.server.dosopt.seminar.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ServiceMember {

    @Id
    @GeneratedValue
    private Long id;

    private String nickanme;
    private String password;

    @Builder
    public ServiceMember(String nickanme, String password) {
        this.nickanme = nickanme;
        this.password = password;
    }
}