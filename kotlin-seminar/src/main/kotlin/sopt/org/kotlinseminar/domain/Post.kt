package sopt.org.kotlinseminar.domain

import jakarta.persistence.*
import lombok.Builder

@Entity
class Post(
    title: String,

    content: String,

    member: Member,

    imageUrl: String?,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
): BaseTimeEntity() {

    @Column
    var title = title
        protected set

    @Column(columnDefinition = "TEXT")
    var content = content
        protected set

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "member_id")
    var member = member
        protected set

    @Column
    var imageUrl = imageUrl
        protected set

    fun updateContent(content: String) {
        this.content = content
    }
}