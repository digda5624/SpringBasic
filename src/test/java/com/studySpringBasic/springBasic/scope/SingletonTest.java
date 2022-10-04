package com.studySpringBasic.springBasic.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    void singletonBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean bean1 = ac.getBean(SingletonBean.class);
        SingletonBean bean2 = ac.getBean(SingletonBean.class);

        Assertions.assertThat(bean1).isSameAs(bean2);
        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean {

        @PostConstruct
        void init(){
            System.out.println("SingletonBean init");
        }

        @PreDestroy
        void destroy(){
            System.out.println("SingletonBean destroy");
        }

        public SingletonBean() {
            System.out.println("singleton Bean 생성자 호출");
        }
    }
}
