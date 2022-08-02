package com.redjen.instagram.repository;

import com.redjen.instagram.domain.entity.Member;
import com.redjen.instagram.domain.entity.Post;
import com.redjen.instagram.domain.entity.PostPhoto;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post savePost(Post newPost, List<PostPhoto> postPhotoList);
    Post modifyPost(Post oldPost, String newBody);
    Long removePost(Post deletePost);
    Post findPostById(Long postId);
    Optional<List<Post>> findPostListByMember(Member member);

}
