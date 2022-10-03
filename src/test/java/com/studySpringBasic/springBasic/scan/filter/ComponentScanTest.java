package com.studySpringBasic.springBasic.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentScanTest {

    @Test
    @DisplayName("[sucess] 어노테이션기반_검색")
    public void 어노테이션기반_검색(){
        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentScanClass.class);
        // when

        BeanA beanA = ac.getBean(BeanA.class);
        //BeanB beanB = ac.getBean(BeanB.class);
        // then
        assertThat(beanA).isNotNull();
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean(BeanB.class)
                );
    }

    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyAnnotation.class),
            excludeFilters = @ComponentScan.Filter(classes = MyExcludeAnnotation.class)
    )
    public static class ComponentScanClass{

    }
}
