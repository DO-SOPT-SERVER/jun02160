package sopt.org.kotlinseminar.dto.request.post

import jakarta.validation.Constraint


@MustBeDocumented
@Constraint(validatedBy = [TitleValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidTitle(
        val message: String = "Invalid title",
        val pattern: String = "[가-힣|a-z|A-Z|0-9|]"
)