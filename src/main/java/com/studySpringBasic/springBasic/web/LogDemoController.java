package com.studySpringBasic.springBasic.web;

import com.studySpringBasic.springBasic.common.MyLogger;
import com.studySpringBasic.springBasic.common.SessionLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;
    private final SessionLogger sessionLogger;


    @GetMapping("log-demo")
    public String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURI();
        HttpSession session = request.getSession();
//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);
        sessionLogger.setSessionId(session.getId());

        sessionLogger.log(session.getId());

        myLogger.log("controller test");
        logDemoService.logic("test ID");

        return "success";
    }
}
