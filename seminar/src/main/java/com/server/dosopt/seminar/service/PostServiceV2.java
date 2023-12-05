package com.server.dosopt.seminar.service;

import com.server.dosopt.seminar.common.exception.BusinessException;
import com.server.dosopt.seminar.domain.Category;
import com.server.dosopt.seminar.domain.Member;
import com.server.dosopt.seminar.domain.Post;
import com.server.dosopt.seminar.dto.request.post.PostCreateRequest;
import com.server.dosopt.seminar.dto.request.post.PostUpdateRequest;
import com.server.dosopt.seminar.dto.response.post.PostGetResponse;
import com.server.dosopt.seminar.external.S3Service;
import com.server.dosopt.seminar.repository.MemberRepository;
import com.server.dosopt.seminar.repository.PostJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceV2 {

    // 이미지를 폴더링 해서 올리는 방식이 속도 측면에서 더 빠름!
    // "posts/1member/1.jpg", "posts/2member/1.jpg", "posts/3member/1.jpg" -> 이런식으로 폴더의 네이밍을 사용하는 것이 조회 속도 측면에서 더 우수
    private static final String POST_IMAGE_FOLDER_NAME = "posts/";

    private final PostJpaRepository postRepository;
    private final MemberRepository memberRepository;  // 연관관계 매핑이 되어 있는 Member의 레포지토리도 주입받아서 사용
    private final CategoryService categoryService;
    private final S3Service s3Service;

    @Transactional
    public String createV2(PostCreateRequest reqeust, MultipartFile image, Long memberId) {
        try {
            final String imageUrl = s3Service.uploadImage(POST_IMAGE_FOLDER_NAME, image);
            Member member = memberRepository.findByIdOrThrow(memberId);
            Post post = postRepository.save(
                    Post.builderWithImageUrl()
                            .title(reqeust.title())
                            .content(reqeust.content())
                            .imageUrl(imageUrl)
                            .member(member)
                            .build());
            return post.getPostId().toString();
        } catch (RuntimeException | IOException e) {  // Checked Exception이므로 try-catch로 잡아서 런타임으로 던져줘야 한다!
            throw new RuntimeException(e.getMessage());
        }

    }

    @Transactional
    public void deleteByIdV2(Long postId) {
        try {
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new BusinessException("해당하는 게시글이 없습니다."));
            s3Service.deleteImage(post.getImageUrl());
            postRepository.deleteById(postId);
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public PostGetResponse getById(Long postId) {
        Post post = findPostById(postId);
        return PostGetResponse.of(post, getCategoryByPost(post));
    }

    public List<PostGetResponse> getPosts(Long memberId) {
        return postRepository.findAllByMemberId(memberId).stream()
                .map(post -> PostGetResponse.of(post, getCategoryByPost(post)))
                .toList();
    }

    @Transactional
    public void update(PostUpdateRequest request, Long postId) {
        Post post = findPostById(postId);
        post.updateContent(request.content());   // update 이후 save 하지 않아도 반영된다!!
    }

    private Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글입니다."));
    }

    private Category getCategoryByPost(Post post) {
        return categoryService.getByCategoryId(post.getCategoryId());
    }


}
