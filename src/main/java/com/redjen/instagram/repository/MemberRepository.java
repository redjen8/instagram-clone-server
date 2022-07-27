package com.redjen.instagram.repository;

import com.redjen.instagram.domain.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void register(Member member) {
        em.persist(member);
    }

    public Member findOneById(Long id) {
        return em.find(Member.class, id);
    }

    public Member findOneByMemberId(String id) {
        return em.find(Member.class, id);
    }
}
