package com.redjen.instagram.repository;

import com.redjen.instagram.domain.entity.Member;
import com.redjen.instagram.domain.entity.Post;
import com.redjen.instagram.domain.entity.PostPhoto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryImpl implements PostRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Post savePost(Post newPost, List<PostPhoto> postPhotoList) {
        return null;
    }

    @Override
    public Post modifyPost(Post newPost, Member member) {
        return null;
    }

    @Override
    public Long removePost(Post deletePost) {
        return null;
    }

    @Override
    public Post findPostById(Long postId) {
        return null;
    }

    @Override
    public Optional<List<Post>> findPostListByMember(Member member) {
        return Optional.empty();
    }
}
