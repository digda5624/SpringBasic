package com.studySpringBasic.springBasic.provider;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class PrototypeBean {

    private int count;

    public void addCount(){
        count++;
    }

    public PrototypeBean() {
        this.count = 0;
    }

    public int getCount() {
        return count;
    }
}
