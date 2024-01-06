package com.server.dosopt.seminar.dto.request.member;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 1. 직접 어노테이션을 생성하여 커스텀하는 방법
 * 2. Spring 내장 Validator 인터페이스를 구현하는 방법
 * 3. Spring Validator 라이브러리에서 지원하는 어노테이션을 적용하는 방
 * 4. 라이브러리 추가 없이 직접 로직을 작성하는 방법
 */
public class MemberNameValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
