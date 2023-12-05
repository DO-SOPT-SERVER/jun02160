package com.server.dosopt.seminar.dto.request.post;

import jakarta.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = TitleValidator.class)
@Target({java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTitle {

    String message() default "유효하지 않은 제목입니다.";

    String pattern() default "[가-힣|a-z|A-Z|0-9|]";
}
