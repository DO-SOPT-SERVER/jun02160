package com.server.dosopt.seminar.service;

import com.server.dosopt.seminar.domain.Member;
import com.server.dosopt.seminar.domain.Post;
import com.server.dosopt.seminar.dto.request.post.PostCreateRequest;
import com.server.dosopt.seminar.dto.request.post.PostUpdateRequest;
import com.server.dosopt.seminar.dto.response.post.PostGetResponse;
import com.server.dosopt.seminar.repository.MemberRepository;
import com.server.dosopt.seminar.repository.PostJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostJpaRepository postRepository;
    private final MemberRepository memberRepository;  // 연관관계 매핑이 되어 있는 Member의 레포지토리도 주입받아서 사용

    @Transactional
    public String create(PostCreateRequest reqeust, Long memberId) {
        Member member = memberRepository.findByIdOrThrow(memberId);
        Post post = postRepository.save(
                Post.builder()
                        .member(member)
                        .title(reqeust.title())
                        .content(reqeust.content()).build());
        return post.getPostId().toString();

    }

    public PostGetResponse getById(Long postId) {
        Post post = findPostById(postId);
        return PostGetResponse.of(post);
    }

    public List<PostGetResponse> getPosts(Long memberId) {
        return postRepository.findAllByMemberId(memberId).stream()
                .map(post -> PostGetResponse.of(post))
                .toList();
    }

    @Transactional
    public void update(PostUpdateRequest request, Long postId) {
        Post post = findPostById(postId);
        post.updateContent(request.content());   // update 이후 save 하지 않아도 반영된다!!
    }

    @Transactional
    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }

    private Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글입니다."));
    }


}
