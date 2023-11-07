package sopt.org.kotlinseminar.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
class Sopt(
    generation: Int,

    @Column
    @Enumerated(EnumType.STRING)
    val part: Part
) {
    final var generation = generation
        private set
}