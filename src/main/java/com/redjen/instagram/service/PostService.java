package com.redjen.instagram.service;

import com.redjen.instagram.domain.common.CustomException;
import com.redjen.instagram.domain.common.ErrorCode;
import com.redjen.instagram.domain.entity.Member;
import com.redjen.instagram.domain.entity.Post;
import com.redjen.instagram.domain.entity.PostPhoto;
import com.redjen.instagram.domain.post.WritePostParam;
import com.redjen.instagram.repository.MemberRepository;
import com.redjen.instagram.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    public Post writeNewPost(WritePostParam writePostParam) {
        Optional<Member> author = memberRepository.findOneById(writePostParam.getAuthorId());
        if (author.isEmpty()) throw new CustomException(ErrorCode.POST_MEMBER_ID_NOT_EXIST);
        List<String> photoUrlList = writePostParam.getPostPhotoUrlList();
        List<PostPhoto> postPhotoList = new ArrayList<>();
        for (String eachUrl : photoUrlList) {
            postPhotoList.add(PostPhoto.builder().url(eachUrl).build());
        }

        Post newPost = Post.builder()
                .author(author.get())
                .body(writePostParam.getPostBody())
                .createdAt(LocalDateTime.now())
                .photoList(postPhotoList)
                .build();
        return postRepository.savePost(newPost, postPhotoList);
    }

    public Post modifyPost() {
        return null;
    }

    public List<Post> getPostList() {
        return null;
    }

    public Boolean deletePost() {
        return true;
    }
}
