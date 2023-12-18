package com.server.dosopt.seminar.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * '연관관계의 주인은?'에 FOCUS
 * = 외래키를 누가 가지고 있는가!
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "post")  // CamelCase -> snake_case 형식으로 자동 변환
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;

    @Column(columnDefinition = "TEXT")  // default: VARCHAR -> TEXT는 이와 같이 명시해주는 게 좋음
    private String content;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")   // @Column 어노테이션에서 DDL 기본 옵션까지 지정할 수 있도록 지일
    private Member member;   // 엔티티에서는 매핑할 객체 그대로 적어주면 된다! (ID를 적는 것 X)

//    @ManyToOne 사용이 아닌 논리적으로 관계만 맺어두기 위함
    @Column(name = "category_id")
    private CategoryId categoryId;

    @Builder
    public Post(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    // 빌더 패턴 사용 시 메소드를 지정하여 빌더패턴을 인식하지 못하는 문제 해결
    @Builder(builderMethodName = "builderWithImageUrl", buildMethodName = "buildWithImageUrl")
    public Post(String title, String content, String imageUrl, Member member) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.member = member;
    }



    public void updateContent(String content) {
        this.content = content;
    }

    public void addCategory(CategoryId categoryId) {
        this.categoryId = categoryId;
    }
}
