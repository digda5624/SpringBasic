package com.studySpringBasic.springBasic.singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SingletonPattern {

    // 자기 자신에 대한 참조를 일으킴
    private static final SingletonPattern instance = new SingletonPattern();

    public static SingletonPattern getInstance() {
        log();
        return instance;
    }

    // 핵심 private 으로 생성자를 막았다.
    private SingletonPattern() {
    }

    private static void log(){
        log.info("싱글톤 객체 호출");
    }

}
