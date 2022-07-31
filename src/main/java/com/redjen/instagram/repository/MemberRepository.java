package com.redjen.instagram.repository;

import com.redjen.instagram.domain.common.CustomException;
import com.redjen.instagram.domain.common.CustomUserDetails;
import com.redjen.instagram.domain.common.ErrorCode;
import com.redjen.instagram.domain.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

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

    public Optional<Member> findOneByMemberId(String userId) {
        return Optional.of(em.find(Member.class, userId));
    }

    public String updateRefreshToken(Long id, String newToken) {
        Member updateMember = em.find(Member.class, id);
        updateMember.setRefreshToken(newToken);
        return newToken;
    }

    public Optional<CustomUserDetails> convertMemberToCustomUserDetail(String userId) {
        Member findMember;
        Optional<Member> findMemberByMemberId = findOneByMemberId(userId);
        if (findMemberByMemberId.isPresent()) {
            findMember = findMemberByMemberId.get();
        } else {
            throw new CustomException(ErrorCode.LOGIN_ID_NOT_EXIST);
        }
        return Optional.ofNullable(CustomUserDetails.builder()
                .id(userId)
                .name(findMember.getName())
                .password(findMember.getPassword())
                .enabled(true)
                .build());
    }
}
