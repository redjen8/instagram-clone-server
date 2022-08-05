package com.redjen.instagram.service;

import com.redjen.instagram.domain.common.CustomException;
import com.redjen.instagram.domain.common.ErrorCode;
import com.redjen.instagram.domain.entity.Member;
import com.redjen.instagram.domain.entity.Post;
import com.redjen.instagram.domain.entity.PostPhoto;
import com.redjen.instagram.domain.post.ModifyPostParam;
import com.redjen.instagram.domain.post.WritePostParam;
import com.redjen.instagram.repository.MemberRepository;
import com.redjen.instagram.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Post modifyPost(ModifyPostParam modifyPostParam) {
        Post existPost = postRepository.findPostById(modifyPostParam.getPostId());
        Optional<Member> authorMember = memberRepository.findOneById(modifyPostParam.getUserId());
        authorMember.orElseThrow(() -> new CustomException(ErrorCode.POST_MEMBER_ID_NOT_EXIST));

        if (!existPost.getAuthor().equals(authorMember.get())) throw new CustomException(ErrorCode.POST_MODIFY_MEMBER_ID_UNAUTHORIZED);

        return postRepository.modifyPost(existPost, modifyPostParam.getNewPostBody());
    }

    public Boolean deletePost() {
        return true;
    }
}
