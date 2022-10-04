package com.studySpringBasic.springBasic.web;

import com.studySpringBasic.springBasic.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Provider;
import java.io.Serializable;

@RequiredArgsConstructor
@Service
public class LogDemoService {

//    private final Provider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;

    public void logic(Serializable id) {
//        MyLogger myLogger = myLoggerProvider.get();
        myLogger.log("service id = " + id);
    }

    @PostConstruct
    void init(){
        Class<? extends MyLogger> aClass = myLogger.getClass();

        System.out.println("aClass = " + aClass);
    }
}
