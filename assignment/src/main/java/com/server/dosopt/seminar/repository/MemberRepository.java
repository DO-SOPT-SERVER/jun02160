package com.server.dosopt.seminar.repository;

import com.server.dosopt.seminar.common.exception.BusinessException;
import com.server.dosopt.seminar.domain.Member;
import com.server.dosopt.seminar.enums.ErrorMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 인터페이스에서 구현부를 작성하기 위해 default 키워드를 붙여 메서드 추가
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    default Member findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new BusinessException(ErrorMessage.NOT_FOUND_USER)
        );
    }
}
