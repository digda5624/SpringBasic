package com.studySpringBasic.springBasic.singleton;

import com.studySpringBasic.springBasic.app.Application;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("[success] 싱글톤의_주의사항")
    public void 싱글톤의_주의사항(){
        // given
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        // when
        StatefulService bean = context.getBean(StatefulService.class);
        bean.order("주문1", 1000);
        bean.order("주문2", 3000);
        // then
        // 주문 금액 조회 하기 현재 동시성 문제로 인해서 bean 의 가경이 변경 되었다.
        System.out.println("bean.getPrice() = " + bean.getPrice());

        Assertions.assertThat(bean.getPrice() != 1000).isTrue();
        Assertions.assertThat(bean.getPrice()).isEqualTo(3000);
    }

    public static class Config {
        @Bean
        StatefulService statefulService(){
            return new StatefulService();
        }
    }

}