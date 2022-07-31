package com.redjen.instagram.repository;

import com.redjen.instagram.domain.common.CustomUserDetails;
import com.redjen.instagram.domain.entity.Member;

import java.util.Optional;

public interface MemberRepository {

    void register(Member member);
    Member findOneById(Long id);
    Optional<Member> findOneByMemberId(String userId);
    String updateRefreshToken(Long id, String newToken);
    Optional<CustomUserDetails> convertMemberToCustomUserDetail(String userId);

}
