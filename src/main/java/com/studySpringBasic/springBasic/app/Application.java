package com.studySpringBasic.springBasic.app;

import com.studySpringBasic.springBasic.config.AppConfig;
import com.studySpringBasic.springBasic.config.ConfigSpring;
import com.studySpringBasic.springBasic.member.service.MemberService;
import com.studySpringBasic.springBasic.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    /**
     * 기존에는 개발자가 직접 자바 코드로 모든 것을 했다면 이제부터는 스프링 컨테이너에 객체를 스프링 빈으로 등록하고
     * 스프링 컨테이너에서 스프링 빈을 찾아서 사용하도록 변경되었다.
     */
    public static void main(String[] args) {
        // 어노테이션 기반으로 Context 를 만들어라 그외에도 xml 형식으로 만드는 것도 존재함
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigSpring.class);

        MemberService memberService = applicationContext.getBean(MemberService.class);
        OrderService orderService = applicationContext.getBean(OrderService.class);

    }

}
