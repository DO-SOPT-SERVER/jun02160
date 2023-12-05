package com.server.dosopt.seminar.repository;

import com.server.dosopt.seminar.domain.ServiceMember;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceMemberRepository extends JpaRepository<ServiceMember, Long> {

    Optional<ServiceMember> findByNickname(String nickname);
}
