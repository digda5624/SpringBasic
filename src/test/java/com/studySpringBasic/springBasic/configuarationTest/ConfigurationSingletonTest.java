package com.studySpringBasic.springBasic.configuarationTest;

import com.studySpringBasic.springBasic.config.AppConfig;
import com.studySpringBasic.springBasic.config.ConfigSpring;
import com.studySpringBasic.springBasic.member.repository.MemberRepository;
import com.studySpringBasic.springBasic.member.repository.MemberServiceImpl;
import com.studySpringBasic.springBasic.member.service.MemberService;
import com.studySpringBasic.springBasic.order.OrderService;
import com.studySpringBasic.springBasic.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("[success] Configuration_싱글톤_생성_과정")
    public void Configuration_싱글톤_생성_과정(){
        // given
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigSpring.class);

        MemberServiceImpl memberService = context.getBean(MemberServiceImpl.class);
        OrderServiceImpl orderService = context.getBean(OrderServiceImpl.class);

        // when
        MemberRepository memberRepository = memberService.getMemberRepository();
        MemberRepository memberRepository1 = orderService.getMemberRepository();

        // then

        System.out.println("memberService.getClass() = " + memberService.getClass());
        System.out.println("memberRepository1.getClass() = " + memberRepository1.getClass());
        Assertions.assertThat(memberRepository).isSameAs(memberRepository1);
    }

    @Test
    @DisplayName("[success] 스프링bean_Configuration은_CGLIB이다")
    public void 스프링bean_Configuration은_CGLIB이다(){
        // given
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigSpring.class);
        // when
        ConfigSpring bean = context.getBean(ConfigSpring.class);
        // then
        System.out.println("bean.getClass() = " + bean.getClass());

        /**
         * Configuration 객체는 싱글톤을 프록시를 사용해서 보장한다.
         *
         * 예상 코드를 살펴보자
         * @Bean
         * public MemberRepository memberRepository(){
         *  if (memoryMemberRepository 가 이미 스프링 컨테이너에 등록되어 있다면?) {
         *      return 스프링 컨테이너에 찾아서 반환
         *      }
         *  else {
         *      기존 @Bean에 작성한 로직을 수행한다.
         *      }
         * }
         */
    }
}
