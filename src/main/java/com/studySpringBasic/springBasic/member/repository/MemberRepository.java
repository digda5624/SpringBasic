package com.studySpringBasic.springBasic.member.repository;

import com.studySpringBasic.springBasic.entity.Member;

/**
 * 회원 정보 조회 및 추가 관련 interface
 */
public interface MemberRepository {

    void save(Member member);

    Member findById(Long id);
}
