package com.studySpringBasic.springBasic.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionLogger {

    private String sessionId;
    private String requestURL;

    public void setSessionId(String sessionId){
        this.sessionId = sessionId;
    }

    public void log(String message){
        System.out.println("[" + sessionId + "] " + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init(){
        sessionId = UUID.randomUUID().toString();
        System.out.println("[" + sessionId + "] " + "session scope bean create: " + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("[" + sessionId + "] " + "session scope bean close: " + this);
    }
}
