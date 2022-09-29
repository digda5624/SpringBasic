package com.studySpringBasic.springBasic.member.service;

import com.studySpringBasic.springBasic.entity.Member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long id);
}
