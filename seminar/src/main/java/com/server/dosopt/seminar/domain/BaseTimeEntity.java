package com.server.dosopt.seminar.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 모든 엔티티에 공통적으로 포함되어야 할 필수 속성 : 생성일시, 수정일시
 * -> 이를 추상 클래스로 생성하여 공용할 수 있도록 하자!
 *
 * JPA에서 기본적으로 자주 사용하는 컬럼을 어노테이션으로 만들어 둠 - @CreatedDate, @LastModifiedDate
 * -> LocalDateo.now() 로 현재시각 초기화를 해줘야 하는데, 이 어노테이션에서 대신 해준다!
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
