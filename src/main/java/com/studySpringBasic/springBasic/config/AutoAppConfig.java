package com.studySpringBasic.springBasic.config;

import com.studySpringBasic.springBasic.member.repository.MemberRepository;
import com.studySpringBasic.springBasic.member.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @Component 인 애들을 전부 스캔해서 등록한다.
// 예제 코드를 유지시키기 위해서 @Configuration 달린 애들 제외
@ComponentScan(
        basePackages = "com.studySpringBasic.springBasic",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Configuration.class})
)
public class AutoAppConfig {

//    @Bean
//    MemberRepository memoryMemberRepository(){
//        return new MemoryMemberRepository();
//    }
}
