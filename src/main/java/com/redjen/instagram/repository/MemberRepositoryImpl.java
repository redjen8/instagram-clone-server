package com.redjen.instagram.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.redjen.instagram.domain.common.CustomException;
import com.redjen.instagram.domain.common.CustomUserDetails;
import com.redjen.instagram.domain.common.ErrorCode;
import com.redjen.instagram.domain.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static com.redjen.instagram.domain.entity.QMember.member;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceContext
    private EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;

    public MemberRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public void register(Member member) {
        em.persist(member);
    }

    public Optional<Member> findOneById(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    public Optional<Member> findOneByMemberId(String userId) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(member)
                .where(member.userId.eq(userId))
                .fetchOne());
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

    @Override
    public Boolean checkMemberExists(Long id) {
        Optional<Member> findMember = Optional.ofNullable(em.find(Member.class, id));
        return findMember.isEmpty();
    }
}
