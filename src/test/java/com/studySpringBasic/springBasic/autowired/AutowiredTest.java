package com.studySpringBasic.springBasic.autowired;

import com.studySpringBasic.springBasic.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    @DisplayName("[sucess] Autowired_테스트")
    public void Autowired_테스트(){
        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class, TestBean2.class);
        // when

        // then
    }

    private static class TestBean2{

    }

    private static class TestBean {

        TestBean2 testBean2;

        @Autowired
        public TestBean(TestBean2 testBean2) {
            System.out.println("testBean2 = " + testBean2);
            this.testBean2 = testBean2;
        }

        @Autowired
        public void setTestBean(TestBean2 testBean){
            System.out.println("testBean = " + testBean);
        }

        @Autowired(required = false)
        public void setNoBean1(Member member){
            System.out.println("testBean2 = " + testBean2);
            System.out.println("setNoBean1 = " + member);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member){
            System.out.println("testBean2 = " + testBean2);
            System.out.println("setNoBean2 = " + member);
        }

        @Autowired(required = false)
        public void setNoBean3(Optional<Member> member){
            System.out.println("testBean2 = " + testBean2);
            System.out.println("setNoBean3 = " + member);
        }
    }
}
