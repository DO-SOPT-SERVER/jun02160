package com.server.dosopt.seminar.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * main함수가 있는 애플리케이션 서버의 시작점 클래스에 달아줄 수 있지만,
 * 너무 복잡해지고 가독성이 떨어지는 것을 대비하여 따로 Config 클래스로 생성하여 관리 가능
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
}
