package com.studySpringBasic.springBasic.provider;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonUsePrototypeTest {

    @Test
    public void test(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, SingletonUsePrototype.class);
        SingletonUsePrototype bean2 = ac.getBean(SingletonUsePrototype.class);
        SingletonUsePrototype bean1 = ac.getBean(SingletonUsePrototype.class);

        Assertions.assertThat(bean1.logic()).isEqualTo(1);
        Assertions.assertThat(bean2.logic()).isEqualTo(2);

        // 다른 주소공간을 가지게 창조
        Assertions.assertThat(bean1.useApplicationContext()).isEqualTo(1);
        Assertions.assertThat(bean2.useApplicationContext()).isEqualTo(1);

        // DL(Dependency Lookup) 기능 제공 하는 spring
        Assertions.assertThat(bean1.useObjectProvider()).isEqualTo(1);
        Assertions.assertThat(bean2.useObjectProvider()).isEqualTo(1);

        // javax 사용하기
        Assertions.assertThat(bean1.useJavaxProvider()).isEqualTo(1);
        Assertions.assertThat(bean2.useJavaxProvider()).isEqualTo(1);
    }
}
