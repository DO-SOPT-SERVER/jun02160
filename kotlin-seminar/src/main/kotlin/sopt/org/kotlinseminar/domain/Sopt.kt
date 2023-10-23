package sopt.org.kotlinseminar.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
class Sopt(
    var generation: Int,

    @Column
    @Enumerated(EnumType.STRING)
    var part: Part
) {

}