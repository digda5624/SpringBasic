package com.studySpringBasic.springBasic.provider;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.inject.Provider;

public class SingletonUsePrototype {

    private final PrototypeBean prototypeBean;
    private final ApplicationContext applicationContext;

    @Autowired
    private ObjectProvider<PrototypeBean> prototypeBeanProvider;
    @Autowired
    private Provider<PrototypeBean> beanProvider;

    @Autowired
    public SingletonUsePrototype(PrototypeBean prototypeBean, ApplicationContext applicationContext) {
        this.prototypeBean = prototypeBean;
        this.applicationContext = applicationContext;
    }

    public int logic(){
        // 싱글톤이기 때문에 prototype 이여도 같은 주소 공간을 참조하고 있기 때문에 싱글톤 처럼 동작한다.
        prototypeBean.addCount();
        System.out.println("prototypeBean = " + prototypeBean);
        return prototypeBean.getCount();
    }

    public int useApplicationContext(){
        // getBean 해서 프로토타입 가져와서 재할
        PrototypeBean bean = applicationContext.getBean(PrototypeBean.class);
        bean.addCount();
        System.out.println("bean = " + bean);
        return bean.getCount();
    }

    public int useObjectProvider(){
        PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
        prototypeBean.addCount();
        System.out.println("prototypeBean = " + prototypeBean);
        return prototypeBean.getCount();
    }

    public int useJavaxProvider(){
        PrototypeBean prototypeBean = beanProvider.get();
        prototypeBean.addCount();
        System.out.println("prototypeBean = " + prototypeBean);
        return prototypeBean.getCount();
    }

}
