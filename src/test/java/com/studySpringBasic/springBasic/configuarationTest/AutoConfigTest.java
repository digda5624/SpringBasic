package com.studySpringBasic.springBasic.configuarationTest;

import com.studySpringBasic.springBasic.config.AutoAppConfig;
import com.studySpringBasic.springBasic.member.repository.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoConfigTest {

    @Test
    @DisplayName("[sucess] ComponentScan을_통한_빈생성")
    public void ComponentScan을_통한_빈생성(){
        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        // when

        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
        // then
    }
}
