package com.redjen.instagram.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.redjen.instagram.domain.entity.Member;
import com.redjen.instagram.domain.entity.Post;
import com.redjen.instagram.domain.entity.PostPhoto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static com.redjen.instagram.domain.entity.QPost.post;

@Repository
public class PostRepositoryImpl implements PostRepository {

    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;

    public PostRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Post savePost(Post newPost, List<PostPhoto> postPhotoList) {
        newPost.setPhotoList(postPhotoList);
        em.persist(newPost);
        return newPost;
    }

    @Override
    public Post modifyPost(Post oldPost, String newBody) {
        oldPost.setBody(newBody);
        return oldPost;
    }

    @Override
    public Long removePost(Post deletePost) {
        Long deleteId = deletePost.getPostId();
        em.remove(deletePost);
        return deleteId;
    }

    @Override
    public Post findPostById(Long postId) {
        return em.find(Post.class, postId);
    }

    @Override
    public Optional<List<Post>> findPostListByMember(Member member) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(post)
                .where(post.author.eq(member))
                .stream().toList());
    }
}
