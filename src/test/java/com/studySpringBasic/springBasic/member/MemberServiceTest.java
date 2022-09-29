package com.studySpringBasic.springBasic.member;

import com.studySpringBasic.springBasic.config.AppConfig;
import com.studySpringBasic.springBasic.entity.Grade;
import com.studySpringBasic.springBasic.entity.Member;
import com.studySpringBasic.springBasic.member.repository.MemberServiceImpl;
import com.studySpringBasic.springBasic.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

//    MemberService memberService = new MemberServiceImpl(null);

    MemberService memberService;

    @BeforeEach
    void init(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    @DisplayName("[sucess] 멤버서비스 조회 삭제 성공")
    public void 멤버서비스_조회_삭제_성공(){
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then

    }
}
