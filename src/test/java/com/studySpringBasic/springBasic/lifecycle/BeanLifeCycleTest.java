package com.studySpringBasic.springBasic.lifecycle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    @DisplayName("[sucess] 라이프_사이클_테스트")
    public void 라이프_사이클_테스트(){
        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(BeanLifeCycle.class);
        // when
        NetworkClient client = ac.getBean(NetworkClient.class);
        client.connect();
        ac.close();
        // then
    }


    @Configuration
    static class BeanLifeCycle {

        @Bean(initMethod = "afterPropertiesSet", destroyMethod = "destroy")
        public NetworkClient networkClient(){
            // spring 은 다음과 같은 의존관계 주입 순서를 가진다.

            // 1. 객체 생성
            NetworkClient networkClient = new NetworkClient();

            // 2. 의존관계 주입 완료 여기서는 setter 로 퉁치자 물론 예시가 부정확하지만 대략 이런 느낌이라고 생각하자.
            networkClient.setUrl("http://digda5624/test");
            return networkClient;
        }
    }

}
