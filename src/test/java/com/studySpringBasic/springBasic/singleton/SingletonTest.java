package com.studySpringBasic.springBasic.singleton;

import com.studySpringBasic.springBasic.config.AppConfig;
import com.studySpringBasic.springBasic.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("[success] 스프링_없는_순수한_DI_컨테이너")
    public void 스프링_없는_순수한_DI_컨테이너(){
        // given
        AppConfig appConfig = new AppConfig();

        // when
        MemberService memberServiceA = appConfig.memberService();
        MemberService memberServiceB = appConfig.memberService();

        // then
        Assertions.assertThat(memberServiceA == memberServiceB).isFalse();
    }

    @Test
    @DisplayName("[success] 싱글톤_객체_사용")
    public void 싱글톤_객체_사용(){
        // given
        SingletonPattern instanceA = SingletonPattern.getInstance();
        SingletonPattern instanceB = SingletonPattern.getInstance();
        // when

        // then
        Assertions.assertThat(instanceA == instanceB).isTrue();
        Assertions.assertThat(instanceA).isSameAs(instanceB);
    }
}
