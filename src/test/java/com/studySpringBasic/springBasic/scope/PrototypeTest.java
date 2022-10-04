package com.studySpringBasic.springBasic.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void singletonBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        Assertions.assertThat(bean1).isNotSameAs(bean2);
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        void init(){
            System.out.println("PrototypeBean init");
        }

        @PreDestroy // 실행 되지 않는다.
        void destroy(){
            System.out.println("PrototypeBean destroy");
        }

        public PrototypeBean() {
            System.out.println("PrototypeBean 생성자 호출");
        }
    }


}
